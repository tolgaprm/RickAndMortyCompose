package com.prmto.rickandmortycompose.presentation.screen.character_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _characterDetailState = mutableStateOf<CharacterDetailState>(CharacterDetailState())
    val characterDetailState: State<CharacterDetailState> get() = _characterDetailState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val characterId = savedStateHandle.get<Int>(CHARACTER_DETAIL_ARGUMENT_KEY)
            characterId?.let {
                getCharacter(characterId = it)
            }
        }

    }

    fun onClickRetry() {
        val characterId = savedStateHandle.get<Int>(CHARACTER_DETAIL_ARGUMENT_KEY)
        _characterDetailState.value = _characterDetailState.value.copy(
            isError = false
        )
        characterId?.let {
            getCharacter(characterId = it)
        }

    }

    private fun getCharacter(characterId: Int) {

        viewModelScope.launch {
            useCases.getCharacterDetailUseCase(characterId = characterId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _characterDetailState.value = characterDetailState.value.copy(
                                isLoading = true,
                            )
                        }
                        is Resource.Success -> {
                            _characterDetailState.value =
                                characterDetailState.value.copy(character = it.data)
                            if (it.data != null) getEpisode(it.data.episode)
                        }
                        is Resource.Error -> {
                            _characterDetailState.value =
                                characterDetailState.value.copy(isError = true)
                        }
                    }
                }
        }

    }

    private fun getEpisode(episodes: List<String>) {
        Log.d("CharacterDetail", "episode")
        _characterDetailState.value = characterDetailState.value.copy(
            isLoading = true,
        )

        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Episode> = mutableListOf()

            episodes.forEach { episodeUrl ->
                val episodeId = (episodeUrl.split("/"))[5]
                val episode = useCases.getEpisodeUseCase(episodeId = episodeId.toInt())
                list.add(episode)
            }

            _characterDetailState.value = characterDetailState.value.copy(
                isLoading = false,
                episodes = list
            )
        }


    }


}