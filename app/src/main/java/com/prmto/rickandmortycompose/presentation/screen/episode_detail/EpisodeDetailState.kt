package com.prmto.rickandmortycompose.presentation.screen.episode_detail

import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.EpisodeDetail

data class EpisodeDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val episodeDetail: EpisodeDetail? = null,
    val characters: List<Character> = emptyList()
)