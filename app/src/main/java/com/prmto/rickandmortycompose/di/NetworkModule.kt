package com.prmto.rickandmortycompose.di

import androidx.paging.ExperimentalPagingApi
import com.prmto.rickandmortycompose.data.RickAndMortyAPI
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.data.repository.RemoteDataSourceImpl
import com.prmto.rickandmortycompose.domain.repository.RemoteDataSource
import com.prmto.rickandmortycompose.util.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RickAndMortyAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        rickAndMortyAPI: RickAndMortyAPI,
        rickAndMortyDatabase: RickAndMortyDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            rickAndMortyAPI = rickAndMortyAPI,
            database = rickAndMortyDatabase
        )
    }

}