package com.prmto.rickandmortycompose.data.remote.dto

import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.model.LocationDetail
import com.prmto.rickandmortycompose.navigation.Screen

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)

fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type
    )
}

fun LocationDto.toLocationDetail(): LocationDetail {
    return LocationDetail(
        id = id,
        name = name,
        type = type,
        residents = residents
    )
}
