package com.prmto.rickandmortycompose.domain.repository

import com.prmto.rickandmortycompose.domain.model.enums.ListType
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveListType(listType: ListType)

    fun readListType(): Flow<String>
}