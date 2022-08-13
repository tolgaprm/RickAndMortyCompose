package com.prmto.rickandmortycompose.domain.use_cases.get_all_characters

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getAllCharacters()
    }
}