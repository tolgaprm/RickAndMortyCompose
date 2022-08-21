package com.prmto.rickandmortycompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.presentation.screen.character.CharacterScreen
import com.prmto.rickandmortycompose.presentation.screen.episode.EpisodeScreen
import com.prmto.rickandmortycompose.presentation.screen.location.LocationScreen

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun NavGraph(
    navController: NavHostController, widthSizeClass: WindowWidthSizeClass,
) {
    NavHost(navController = navController, startDestination = Screen.Character.route) {
        composable(Screen.Character.route) {

            CharacterScreen(
                widthSizeClass = widthSizeClass,
                onClickCharacterItem = {

                }
            )

        }

        composable(Screen.CharacterDetail.route) {

        }

        composable(Screen.Location.route) {
            LocationScreen(
                widthSizeClass = widthSizeClass
            )
        }

        composable(Screen.LocationDetail.route) {

        }

        composable(Screen.Episode.route) {
            EpisodeScreen()
        }
        composable(Screen.EpisodeDetail.route) {

        }


    }
}