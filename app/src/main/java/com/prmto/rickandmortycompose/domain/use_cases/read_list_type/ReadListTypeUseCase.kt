package com.prmto.rickandmortycompose.domain.use_cases.read_list_type

import com.prmto.rickandmortycompose.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadListTypeUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<String> {
        return repository.readListType()
    }
}