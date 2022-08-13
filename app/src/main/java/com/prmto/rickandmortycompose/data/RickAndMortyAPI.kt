package com.prmto.rickandmortycompose.data

import com.prmto.rickandmortycompose.data.remote.dto.CharacterDto
import com.prmto.rickandmortycompose.data.remote.dto.EpisodeDto
import com.prmto.rickandmortycompose.data.remote.dto.LocationDto
import com.prmto.rickandmortycompose.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int? = null
    ): Response<CharacterDto>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDto

    @GET("location")
    suspend fun getAllLocations(): Response<LocationDto>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: Int
    ): LocationDto


    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodeDto>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): EpisodeDto


}