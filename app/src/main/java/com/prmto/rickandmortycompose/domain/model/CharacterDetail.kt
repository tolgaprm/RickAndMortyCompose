package com.prmto.rickandmortycompose.domain.model

import com.prmto.rickandmortycompose.data.remote.dto.CharacterLocation
import com.prmto.rickandmortycompose.data.remote.dto.Origin

data class CharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>
)


fun CharacterDetail.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        image = image,
        species = species,
        gender = gender
    )
}