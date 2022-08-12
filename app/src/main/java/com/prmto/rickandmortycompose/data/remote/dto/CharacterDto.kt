package com.prmto.rickandmortycompose.data.remote.dto

import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.CharacterDetail

data class CharactersDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class Origin(
    val name: String,
    val url: String
)

data class CharacterLocation(
    val name: String,
    val url: String,
)

fun CharactersDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        image = image
    )
}

fun CharactersDto.toCharacterDetail(): CharacterDetail {
    return CharacterDetail(
        id = id,
        name = name,
        status = status,
        image = image,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        episode = episode
    )
}