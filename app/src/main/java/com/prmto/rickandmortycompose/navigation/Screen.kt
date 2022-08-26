package com.prmto.rickandmortycompose.navigation

import com.prmto.rickandmortycompose.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Constant.EPISODE_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Constant.LOCATION_DETAIL_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object Character : Screen("character_screen")

    object CharacterDetail : Screen("character_detail_screen/{$CHARACTER_DETAIL_ARGUMENT_KEY}") {
        fun passCharacterId(characterId: Int): String {
            return "character_detail_screen/$characterId"
        }
    }

    object Location : Screen("location_screen")

    object LocationDetail : Screen("location_detail_screen/{$LOCATION_DETAIL_ARGUMENT_KEY}") {
        fun passLocationId(locationId: Int): String {
            return "location_detail_screen/$locationId"
        }
    }

    object Episode : Screen("episode_screen")

    object EpisodeDetail : Screen("episode_detail_screen/{$EPISODE_DETAIL_ARGUMENT_KEY}") {
        fun passEpisodeId(episodeId: Int): String {
            return "episode_detail_screen/$episodeId"
        }
    }
}
