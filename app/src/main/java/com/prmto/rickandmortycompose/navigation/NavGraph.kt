package com.prmto.rickandmortycompose.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.presentation.screen.character.CharacterScreen
import com.prmto.rickandmortycompose.presentation.screen.character_detail.CharacterDetailScreen
import com.prmto.rickandmortycompose.presentation.screen.episode.EpisodeScreen
import com.prmto.rickandmortycompose.presentation.screen.location.LocationScreen
import com.prmto.rickandmortycompose.presentation.screen.location_detail.LocationDetailScreen
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY
import com.prmto.rickandmortycompose.util.Constant.LOCATION_DETAIL_ARGUMENT_KEY

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
                onClickCharacterItem = { characterId ->
                    Log.d("character", it.toString())
                    navController.navigate(
                        Screen.CharacterDetail.passCharacterId(characterId = characterId)
                    )
                }
            )

        }

        composable(
            Screen.CharacterDetail.route,
            arguments = listOf(
                navArgument(name = CHARACTER_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            CharacterDetailScreen(
                widthSizeClass = widthSizeClass,
                onClickLocationItem = { locationId ->
                    navController.navigate(Screen.LocationDetail.passLocationId(locationId))
                }
            )
        }

        composable(Screen.Location.route) {
            LocationScreen(
                widthSizeClass = widthSizeClass
            ) { locationId ->
                navController.navigate(Screen.LocationDetail.passLocationId(locationId))
            }
        }

        composable(
            Screen.LocationDetail.route,
            arguments = listOf(navArgument(
                name = LOCATION_DETAIL_ARGUMENT_KEY
            ) {
                type = NavType.IntType
            })
        ) {
            LocationDetailScreen(
                widthSizeClass = widthSizeClass
            ) { characterId ->
                navController.navigate(Screen.CharacterDetail.passCharacterId(characterId))
            }
        }

        composable(Screen.Episode.route) {
            EpisodeScreen()
        }
        composable(Screen.EpisodeDetail.route) {

        }


    }
}