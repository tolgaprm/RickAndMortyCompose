package com.prmto.rickandmortycompose.navigation

import com.prmto.rickandmortycompose.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object Character : Screen("character_screen")
    object CharacterDetail : Screen("character_detail_screen/{$CHARACTER_DETAIL_ARGUMENT_KEY}") {
        fun passCharacterId(characterId: Int): String {
            return "character_detail_screen/$characterId"
        }
    }

    object Location : Screen("location_screen")
    object LocationDetail : Screen("location-detail_screen")
    object Episode : Screen("episode_screen")
    object EpisodeDetail : Screen("episode_detail_screen")
}
