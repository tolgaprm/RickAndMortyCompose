package com.prmto.rickandmortycompose.di

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.domain.use_cases.get_all_characters.GetAllCharactersUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_episodes.GetAllEpisodesUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_locations.GetAllLocationsUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_character_detail.GetCharacterDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_episode.GetEpisodeUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_location_detail.GetLocationDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCase(
        repository: Repository
    ): UseCases {
        return UseCases(
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getAllLocationsUseCase = GetAllLocationsUseCase(repository),
            getAllEpisodesUseCase = GetAllEpisodesUseCase(repository),
            getCharacterDetailUseCase = GetCharacterDetailUseCase(repository),
            getEpisodeUseCase = GetEpisodeUseCase(repository),
            getLocationDetailUseCase = GetLocationDetailUseCase(repository)
        )
    }
}