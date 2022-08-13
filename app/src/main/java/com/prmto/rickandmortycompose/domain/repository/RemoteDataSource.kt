package com.prmto.rickandmortycompose.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.prmto.rickandmortycompose.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters() : Flow<PagingData<Character>>

}