package com.prmto.rickandmortycompose.domain.model

sealed class EpisodeListItem() {
    data class EpisodeItem(val episode: Episode) : EpisodeListItem()
    data class SeparatorItem(val season: String) : EpisodeListItem()
}

val EpisodeListItem.EpisodeItem.season: Int
    get() = this.episode.episode.substring(2, 3).toInt()