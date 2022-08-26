package com.prmto.rickandmortycompose.domain.use_cases

import com.prmto.rickandmortycompose.domain.use_cases.get_all_characters.GetAllCharactersUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_episodes.GetAllEpisodesUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_locations.GetAllLocationsUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_character_detail.GetCharacterDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_episode.GetEpisodeUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_episode_detail.GetEpisodeDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_location_detail.GetLocationDetailUseCase

data class UseCases(
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getAllLocationsUseCase: GetAllLocationsUseCase,
    val getAllEpisodesUseCase: GetAllEpisodesUseCase,
    val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    val getEpisodeUseCase: GetEpisodeUseCase,
    val getLocationDetailUseCase: GetLocationDetailUseCase,
    val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
)