package com.prmto.rickandmortycompose.presentation.screen.character

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.domain.model.enums.GenderState
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.domain.model.enums.StatusState
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    val getAllHeroes = useCases.getAllCharactersUseCase()

    private val _characterNameQuery = mutableStateOf("")
    val characterNameQuery: State<String> get() = _characterNameQuery

    private val _statusState = mutableStateOf<StatusState>(StatusState.NONE)
    val statusState: State<StatusState> get() = _statusState

    private val _genderState = mutableStateOf<GenderState>(GenderState.NONE)
    val genderState: State<GenderState> get() = _genderState

    private val _stateListType = mutableStateOf<ListType>(ListType.VERTICAL_GRID)
    val stateListType: State<ListType> get() = _stateListType

    private val _listTypeIcon = mutableStateOf<Int>(R.drawable.grid_list)
    val listTypeIcon: State<Int> get() = _listTypeIcon

    private val _isFilter = mutableStateOf<Boolean>(false)
    val isFilter: State<Boolean> get() = _isFilter


    init {
        viewModelScope.launch {
            readListType().collect { listTypeName ->
                if (listTypeName == Constant.VERTICAL_GRID_NAME){
                    updateListState(ListType.VERTICAL_GRID)
                    updateListTypeIcon(R.drawable.grid_list)
                }else{
                    updateListState(ListType.LIST)
                    updateListTypeIcon(R.drawable.ic_list_icon)
                }
            }
        }
    }


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

    fun checkIfTheFilterHasBeenApplied(): Boolean {

        val statusValue = _statusState.value
        val genderValue = _genderState.value
        val characterName = _characterNameQuery.value

        _isFilter.value =
            !(statusValue == StatusState.NONE && genderValue == GenderState.NONE && characterName == "")

        return _isFilter.value
    }

    fun clearTheFilter() {
        _statusState.value = StatusState.NONE
        _genderState.value = GenderState.NONE
        _characterNameQuery.value = ""
    }

    fun onClickListTypeIcon() {
         viewModelScope.launch {
            if (_stateListType.value == ListType.LIST) {
                updateListState(ListType.VERTICAL_GRID)
                updateListTypeIcon(R.drawable.grid_list)
                saveListTypeInDataStore(listType = ListType.VERTICAL_GRID)
            } else {
                updateListState(ListType.LIST)
                updateListTypeIcon(R.drawable.ic_list_icon)
                saveListTypeInDataStore(listType = ListType.LIST)
            }
        }

    }

    private fun readListType(): Flow<String> {
        return useCases.readListTypeUseCase()
    }

    private fun updateListState(listType: ListType) {
        _stateListType.value = listType
    }

    private fun updateListTypeIcon(@DrawableRes listIcon: Int) {
        _listTypeIcon.value = listIcon
    }

    private suspend fun saveListTypeInDataStore(listType: ListType) {
        useCases.saveListTypeUseCase(listType = listType)
    }

}