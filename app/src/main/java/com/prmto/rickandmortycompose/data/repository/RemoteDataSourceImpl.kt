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
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.repository.RemoteDataSource
import com.prmto.rickandmortycompose.util.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
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

}