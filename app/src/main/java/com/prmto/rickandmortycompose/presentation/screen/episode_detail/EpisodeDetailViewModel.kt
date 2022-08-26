package com.prmto.rickandmortycompose.presentation.screen.episode_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.toCharacter
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.util.Constant
import com.prmto.rickandmortycompose.util.Constant.EPISODE_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<EpisodeDetailState>(EpisodeDetailState())
    val state: State<EpisodeDetailState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val episodeId = savedStateHandle.get<Int>(EPISODE_DETAIL_ARGUMENT_KEY)
            episodeId?.let {
                getEpisode(it)
            }
        }
    }

    fun onRetryClick(){
        val episodeId = savedStateHandle.get<Int>(EPISODE_DETAIL_ARGUMENT_KEY)
        _state.value = _state.value.copy(
            isError = false
        )
        episodeId?.let {
            getEpisode(episodeId = it)
        }
    }

    private fun getEpisode(episodeId: Int) {
        viewModelScope.launch {
            useCases.getEpisodeDetailUseCase(episodeId = episodeId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                episodeDetail = it.data,
                            )

                            it.data?.let { data -> getCharacter(data.characters) }
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

    private fun getCharacter(characters: List<String>) {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Character> = mutableListOf()
            characters.forEach { characterUrl ->
                val characterId = characterUrl.split("/")[5]
                val characterFlow =
                    useCases.getCharacterDetailUseCase(characterId = characterId.toInt())
                characterFlow.collect {
                    it.data?.let { data -> list.add(data.toCharacter()) }
                }
            }

            _state.value = _state.value.copy(
                isLoading = false,
                characters = list
            )
        }
    }
}