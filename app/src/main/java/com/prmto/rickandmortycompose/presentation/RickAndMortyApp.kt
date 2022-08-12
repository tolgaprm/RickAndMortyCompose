package com.prmto.rickandmortycompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prmto.rickandmortycompose.navigation.NavGraph
import com.prmto.rickandmortycompose.navigation.Screen
import com.prmto.rickandmortycompose.presentation.navigation.BottomNav
import com.prmto.rickandmortycompose.presentation.navigation.BottomNavItemData
import com.prmto.rickandmortycompose.presentation.navigation.NavRail
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_ICON_INDEX

@ExperimentalUnitApi
@Composable
fun RickAndMortyApp(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass
) {

    val bottomNavItems = listOf(
        BottomNavItemData.Character,
        BottomNavItemData.Location,
        BottomNavItemData.Episode
    )

    val isCompactScreen = widthSizeClass == WindowWidthSizeClass.Compact
    val isMediumScreen = widthSizeClass == WindowWidthSizeClass.Medium


    Column {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val icons = rememberSaveable(
            saver = listSaver(
                save = { stateList ->
                    if (stateList.isNotEmpty()) {
                        val first = stateList.first()
                        if (!canBeSaved(first)) {
                            throw IllegalStateException("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                        }
                    }
                    stateList.toList()
                },
                restore = { it.toMutableStateList() }
            )
        ) {
            mutableStateListOf(
                BottomNavItemData.Character.selectedIcon,
                BottomNavItemData.Location.unSelectedIcon,
                BottomNavItemData.Episode.unSelectedIcon,
            )
        }

        Row {
            if (isMediumScreen) {
                NavRail(
                    bottomNavItems = bottomNavItems,
                    currentDestination = currentDestination,
                    icons = icons,
                    onHeaderClick = {
                        currentDestination?.let {
                            if (it.route != Screen.Character.route) {
                                navController.navigate(Screen.Character.route)
                                icons[CHARACTER_ICON_INDEX] =
                                    BottomNavItemData.Character.selectedIcon
                            }
                        }

                    }
                ) { route ->
                    navController.navigate(route = route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                    }
                }
            }
            NavGraph(navController = navController)
        }
        if (isCompactScreen) {
            BottomNav(
                bottomNavItems = bottomNavItems,
                currentDestination = currentDestination,
                icons = icons
            ) { route ->
                navController.navigate(route = route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                }
            }
        }
    }
}


