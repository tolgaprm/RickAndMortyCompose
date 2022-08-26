package com.prmto.rickandmortycompose.data.repository

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.domain.model.*
import com.prmto.rickandmortycompose.domain.repository.RemoteDataSource
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getAllCharacters(): Flow<PagingData<Character>> {
        return remoteDataSource.getAllCharacters()
    }

    fun getAllLocations(): Flow<PagingData<Location>> {
        return remoteDataSource.getAllLocations()
    }

    fun getAllEpisodes(): Flow<PagingData<Episode>> {
        return remoteDataSource.getAllEpisodes()
    }

    fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>> {
        return remoteDataSource.getCharacter(characterId = characterId)
    }

    suspend fun getEpisode(episodeId: Int): Episode {
        return remoteDataSource.getEpisode(episodeId = episodeId)
    }

    fun getLocation(locationId: Int): Flow<Resource<LocationDetail>> {
        return remoteDataSource.getLocation(locationId = locationId)
    }

    fun getEpisodeDetail(episodeId: Int): Flow<Resource<EpisodeDetail>> {
        return remoteDataSource.getEpisodeDetail(episodeId = episodeId)
    }
}