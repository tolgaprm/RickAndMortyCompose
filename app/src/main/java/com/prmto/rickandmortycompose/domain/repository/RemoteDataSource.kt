package com.prmto.rickandmortycompose.domain.repository

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(): Flow<PagingData<Character>>

    fun getAllLocations(): Flow<PagingData<Location>>

    fun getAllEpisodes(): Flow<PagingData<Episode>>

}