package com.prmto.rickandmortycompose.presentation.screen.character_detail

import com.prmto.rickandmortycompose.domain.model.CharacterDetail
import com.prmto.rickandmortycompose.domain.model.Episode

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: CharacterDetail? = null,
    val isError: Boolean = false,
    val episodes: List<Episode> = emptyList()
)