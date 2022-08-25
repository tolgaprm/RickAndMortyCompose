package com.prmto.rickandmortycompose.presentation.screen.location_detail

import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.LocationDetail

data class LocationDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val locationDetail: LocationDetail? = null,
    val characterList: List<Character> = emptyList()
)
