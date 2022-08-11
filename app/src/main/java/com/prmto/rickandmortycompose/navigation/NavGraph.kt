package com.prmto.rickandmortycompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prmto.rickandmortycompose.presentation.screen.location.LocationScreen
import com.prmto.rickandmortycompose.presentation.screen.character.CharacterScreen
import com.prmto.rickandmortycompose.presentation.screen.episode.EpisodeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Character.route) {
        composable(Screen.Character.route) {
            CharacterScreen(navController = navController)
        }

        composable(Screen.CharacterDetail.route) {

        }

        composable(Screen.Location.route) {
            LocationScreen(navController = navController)
        }

        composable(Screen.LocationDetail.route) {

        }

        composable(Screen.Episode.route) {
            EpisodeScreen(navController = navController)
        }
        composable(Screen.EpisodeDetail.route) {

        }


    }
}