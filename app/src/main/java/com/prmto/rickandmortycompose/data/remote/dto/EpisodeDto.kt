package com.prmto.rickandmortycompose.data.remote.dto

import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.EpisodeDetail

data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val characters: List<String>,
    val url: String,
    val episode: String,
    val created: String
)

fun List<EpisodeDto>.toEpisode(): List<Episode> {
    return map { episode ->
        Episode(
            id = episode.id,
            name = episode.name,
            air_date = episode.air_date,
            episode = episode.episode
        )
    }
}

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
            id = id,
            name = name,
            air_date = air_date,
            episode = episode
        )

}


fun EpisodeDto.toEpisodeDetail(): EpisodeDetail {
    return EpisodeDetail(
        id = id,
        name = name,
        air_date = air_date,
        episode = episode,
        characters = characters
    )
}