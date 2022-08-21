package com.prmto.rickandmortycompose.data.repository

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.repository.RemoteDataSource
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
}