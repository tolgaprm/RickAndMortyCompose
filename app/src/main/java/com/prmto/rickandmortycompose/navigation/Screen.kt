package com.prmto.rickandmortycompose.navigation

sealed class Screen(val route: String) {
    object Character : Screen("character_screen")
    object CharacterDetail : Screen("character_detail_screen")
    object Location : Screen("location_screen")
    object LocationDetail : Screen("location-detail_screen")
    object Episode : Screen("episode_screen")
    object EpisodeDetail : Screen("episode_detail_screen")
}
