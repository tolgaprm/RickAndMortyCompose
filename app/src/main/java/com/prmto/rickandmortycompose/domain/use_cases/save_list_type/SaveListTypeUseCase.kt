package com.prmto.rickandmortycompose.domain.use_cases.save_list_type

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import javax.inject.Inject

class SaveListTypeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(listType: ListType) {
        repository.saveListType(listType)
    }
}