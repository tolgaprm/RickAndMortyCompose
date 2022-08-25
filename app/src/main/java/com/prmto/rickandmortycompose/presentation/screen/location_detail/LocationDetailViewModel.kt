package com.prmto.rickandmortycompose.presentation.screen.location_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.toCharacter
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.util.Constant
import com.prmto.rickandmortycompose.util.Constant.LOCATION_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<LocationDetailState>(LocationDetailState())
    val state: State<LocationDetailState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val locationId = savedStateHandle.get<Int>(LOCATION_DETAIL_ARGUMENT_KEY)

            locationId?.let {
                getLocation(it)
            }
        }


    }

    fun onRetryClick(){
        val locationId = savedStateHandle.get<Int>(LOCATION_DETAIL_ARGUMENT_KEY)
        _state.value = _state.value.copy(
            isError = false
        )
        locationId?.let {
            getLocation(locationId = it)
        }
    }

    private fun getLocation(locationId: Int) {
        viewModelScope.launch {
            useCases.getLocationDetailUseCase(locationId = locationId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                locationDetail = it.data
                            )
                            it.data?.let { data -> getCharacter(data.residents) }
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                }
        }
    }

    private fun getCharacter(residents: List<String>) {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Character> = mutableListOf()
            residents.forEach { characterUrl ->
                val characterId = characterUrl.split("/")[5]
                val characterFlow =
                    useCases.getCharacterDetailUseCase(characterId = characterId.toInt())
                characterFlow.collect {
                    it.data?.let { data -> list.add(data.toCharacter()) }
                }
            }

            _state.value = _state.value.copy(
                isLoading = false,
                characterList = list
            )
        }
    }
}