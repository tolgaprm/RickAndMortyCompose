package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.lifecycle.ViewModel
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val getAllHeroes = useCases.getAllCharactersUseCase()
}