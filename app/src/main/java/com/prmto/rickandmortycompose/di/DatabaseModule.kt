package com.prmto.rickandmortycompose.di

import android.app.Application
import androidx.room.Room
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.util.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(
        app: Application
    ): RickAndMortyDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            RickAndMortyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}