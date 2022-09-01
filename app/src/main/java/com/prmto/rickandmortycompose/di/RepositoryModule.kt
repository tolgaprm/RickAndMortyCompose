package com.prmto.rickandmortycompose.di

import android.content.Context
import com.prmto.rickandmortycompose.data.repository.DataStoreOperationsImpl
import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.repository.DataStoreOperations
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import com.prmto.rickandmortycompose.domain.use_cases.get_all_characters.GetAllCharactersUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_episodes.GetAllEpisodesUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_all_locations.GetAllLocationsUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_character_detail.GetCharacterDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_episode.GetEpisodeUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_episode_detail.GetEpisodeDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.get_location_detail.GetLocationDetailUseCase
import com.prmto.rickandmortycompose.domain.use_cases.read_list_type.ReadListTypeUseCase
import com.prmto.rickandmortycompose.domain.use_cases.save_list_type.SaveListTypeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

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
            getLocationDetailUseCase = GetLocationDetailUseCase(repository),
            getEpisodeDetailUseCase = GetEpisodeDetailUseCase(repository),
            saveListTypeUseCase = SaveListTypeUseCase(repository),
            readListTypeUseCase = ReadListTypeUseCase(repository)
        )
    }
}