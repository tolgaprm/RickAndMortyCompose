package com.prmto.rickandmortycompose.presentation.screen.character

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.prmto.rickandmortycompose.domain.model.enums.GenderState
import com.prmto.rickandmortycompose.domain.model.enums.StatusState
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val getAllHeroes = useCases.getAllCharactersUseCase()

    private val _characterNameQuery = mutableStateOf("")
    val characterNameQuery: State<String> get() = _characterNameQuery

    fun onChangeCharacterQuery(name: String) {
        _characterNameQuery.value = name
    }

    private val _statusState = mutableStateOf<StatusState>(StatusState.ALIVE)
    val statusState: State<StatusState> get() = _statusState

    fun onStatusOptionState(statusState: StatusState) {
        _statusState.value = statusState
    }

    private val _genderState = mutableStateOf<GenderState>(GenderState.FEMALE)
    val genderState: State<GenderState> get() = _genderState

    fun onGenderOptionState(genderState: GenderState) {
        _genderState.value = genderState
    }

    fun onClickApplyButton() {
        Log.d("characterViewModel", _characterNameQuery.value)
        Log.d("characterViewModel", _statusState.value.title)
        Log.d("characterViewModel", _genderState.value.title)

    }
}