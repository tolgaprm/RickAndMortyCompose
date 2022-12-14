package com.prmto.rickandmortycompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prmto.rickandmortycompose.data.RickAndMortyAPI
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.data.paging_source.CharactersRemoteMediator
import com.prmto.rickandmortycompose.data.paging_source.EpisodeRemoteMediator
import com.prmto.rickandmortycompose.data.paging_source.LocationsRemoteMediator
import com.prmto.rickandmortycompose.data.remote.dto.toCharacterDetail
import com.prmto.rickandmortycompose.data.remote.dto.toEpisode
import com.prmto.rickandmortycompose.data.remote.dto.toEpisodeDetail
import com.prmto.rickandmortycompose.data.remote.dto.toLocationDetail
import com.prmto.rickandmortycompose.domain.model.*
import com.prmto.rickandmortycompose.domain.repository.RemoteDataSource
import com.prmto.rickandmortycompose.util.Constant.ITEMS_PER_PAGE
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalPagingApi
class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyAPI: RickAndMortyAPI,
    private val database: RickAndMortyDatabase
) : RemoteDataSource {

    private val characterDao = database.characterDao()
    private val locationDao = database.locationDao()
    private val episodeDao = database.episodeDao()

    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharactersRemoteMediator(
                rickAndMortyApi = rickAndMortyAPI,
                database = database
            ),
            pagingSourceFactory = { characterDao.getAllCharacters() }
        ).flow
    }

    override fun getAllLocations(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = LocationsRemoteMediator(
                rickAndMortyAPI = rickAndMortyAPI,
                database = database
            ),
            pagingSourceFactory = { locationDao.getAllLocations() }
        ).flow
    }

    override fun getAllEpisodes(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = EpisodeRemoteMediator(
                rickAndMortyAPI = rickAndMortyAPI,
                database = database
            ),
            pagingSourceFactory = { episodeDao.getAllEpisodes() }
        ).flow
    }

    override fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>> {
        return flow {
            emit(Resource.Loading())
            val response = rickAndMortyAPI.getCharacter(id = characterId)
            val characterDetail = response.toCharacterDetail()
            emit(Resource.Success(data = characterDetail))

        }.catch {
            emit(Resource.Error(message = "Does not have a internet connection"))
        }
    }

    override suspend fun getEpisode(episodeId: Int): Episode {
        return rickAndMortyAPI.getEpisode(id = episodeId).toEpisode()
    }

    override fun getLocation(locationId: Int): Flow<Resource<LocationDetail>> {
        return flow {
            emit(Resource.Loading())
            val response = rickAndMortyAPI.getLocation(id = locationId)
            val locationDetail = response.toLocationDetail()
            emit(Resource.Success(data = locationDetail))
        }.catch {
            emit(Resource.Error(message = "Does not have a internet connection"))
        }
    }

    override fun getEpisodeDetail(episodeId: Int): Flow<Resource<EpisodeDetail>> {
        return flow {
            emit(Resource.Loading())
            val response = rickAndMortyAPI.getEpisode(episodeId)
            val episodeDetail = response.toEpisodeDetail()
            emit(Resource.Success(data = episodeDetail))

        }.catch {
            emit(Resource.Error(message = "Does not have a internet connection"))
        }
    }


}