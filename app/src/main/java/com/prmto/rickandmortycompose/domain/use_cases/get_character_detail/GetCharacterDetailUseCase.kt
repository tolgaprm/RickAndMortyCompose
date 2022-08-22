package com.prmto.rickandmortycompose.domain.use_cases.get_character_detail

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.CharacterDetail
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(characterId: Int): Flow<Resource<CharacterDetail>> {
        return repository.getCharacter(characterId = characterId)
    }
}