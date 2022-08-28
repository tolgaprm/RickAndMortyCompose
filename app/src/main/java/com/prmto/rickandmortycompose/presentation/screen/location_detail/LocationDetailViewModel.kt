package com.prmto.rickandmortycompose.presentation.screen.location_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.toCharacter
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.util.Constant.LOCATION_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<LocationDetailState>(LocationDetailState())
    val state: StateFlow<LocationDetailState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val locationId = savedStateHandle.get<Int>(LOCATION_DETAIL_ARGUMENT_KEY)

            locationId?.let {
                getLocation(it)
            }
        }


    }

    fun onRetryClick() {
        val locationId = savedStateHandle.get<Int>(LOCATION_DETAIL_ARGUMENT_KEY)
        _state.update { it.copy(isError = false) }
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
                            _state.update { it.copy(isLoading = true) }
                        }
                        is Resource.Success -> {
                            _state.update { state -> state.copy(locationDetail = it.data) }
                            it.data?.let { data -> getCharacter(data.residents) }
                        }
                        is Resource.Error -> {
                            _state.update { it.copy(isLoading = false, isError = true) }
                        }
                    }
                }
        }
    }

    private fun getCharacter(residents: List<String>) {
        _state.update { it.copy(isLoading = true) }

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

            _state.update { it.copy(isLoading = false, characterList = list) }
        }
    }
}