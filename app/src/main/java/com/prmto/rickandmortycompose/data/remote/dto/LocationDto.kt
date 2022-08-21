package com.prmto.rickandmortycompose.data.remote.dto

import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.model.LocationDetail

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)

fun List<LocationDto>.toLocation(): List<Location> {
    return map { locationDto ->
        Location(
            id = locationDto.id,
            name = locationDto.name,
            type = locationDto.type
        )
    }
}

fun LocationDto.toLocationDetail(): LocationDetail {
    return LocationDetail(
        id = id,
        name = name,
        type = type,
        residents = residents
    )
}
