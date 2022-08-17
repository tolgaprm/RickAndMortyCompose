package com.prmto.rickandmortycompose.presentation.screen.character

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.domain.model.enums.GenderState
import com.prmto.rickandmortycompose.domain.model.enums.ListType
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

    private val _statusState = mutableStateOf<StatusState>(StatusState.ALIVE)
    val statusState: State<StatusState> get() = _statusState

    private val _genderState = mutableStateOf<GenderState>(GenderState.FEMALE)
    val genderState: State<GenderState> get() = _genderState

    private val _stateListType = mutableStateOf<ListType>(ListType.HORIZONTAL_GRID)
    val stateListType: State<ListType> get() = _stateListType

    private val _listTypeIcon = mutableStateOf<Int>(R.drawable.grid_list)
    val listTypeIcon: State<Int> get() = _listTypeIcon

    fun onChangeCharacterQuery(name: String) {
        _characterNameQuery.value = name
    }

    fun onStatusOptionState(statusState: StatusState) {
        _statusState.value = statusState
    }

    fun onGenderOptionState(genderState: GenderState) {
        _genderState.value = genderState
    }

    fun onClickApplyButton() {
        Log.d("characterViewModel", _characterNameQuery.value)
        Log.d("characterViewModel", _statusState.value.title)
        Log.d("characterViewModel", _genderState.value.title)

    }

    fun onClickListTypeIcon() {
        if (_stateListType.value == ListType.LIST) {
            _stateListType.value = ListType.HORIZONTAL_GRID
            _listTypeIcon.value = R.drawable.grid_list
        } else {
            _stateListType.value = ListType.LIST
            _listTypeIcon.value = R.drawable.ic_list_icon
        }
    }
}