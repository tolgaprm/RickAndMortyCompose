package com.prmto.rickandmortycompose.domain.repository

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.domain.model.*
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(): Flow<PagingData<Character>>

    fun getAllLocations(): Flow<PagingData<Location>>

    fun getAllEpisodes(): Flow<PagingData<Episode>>

    fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>>


    suspend fun getEpisode(episodeId: Int): Episode

    fun getLocation(locationId: Int): Flow<Resource<LocationDetail>>


}