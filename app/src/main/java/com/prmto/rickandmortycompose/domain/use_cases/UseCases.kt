package com.prmto.rickandmortycompose.domain.use_cases

import com.prmto.rickandmortycompose.domain.use_cases.get_all_characters.GetAllCharactersUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_locations.GetAllLocationsUseCase

data class UseCases(
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getAllLocationsUseCase: GetAllLocationsUseCase,
)