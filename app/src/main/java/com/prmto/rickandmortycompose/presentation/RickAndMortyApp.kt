package com.prmto.rickandmortycompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.navigation.NavGraph
import com.prmto.rickandmortycompose.navigation.Screen
import com.prmto.rickandmortycompose.presentation.components.BottomSheetContent
import com.prmto.rickandmortycompose.presentation.components.RickAndMortyTopBar
import com.prmto.rickandmortycompose.presentation.navigation.BottomNav
import com.prmto.rickandmortycompose.presentation.navigation.BottomNavItemData
import com.prmto.rickandmortycompose.presentation.navigation.NavRail
import com.prmto.rickandmortycompose.presentation.ui.theme.SHEET_PEEK_HEIGHT
import com.prmto.rickandmortycompose.presentation.ui.theme.Shapes
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_ICON_INDEX
import com.prmto.rickandmortycompose.util.isCompactScreen
import com.prmto.rickandmortycompose.util.isExpandedScreen
import com.prmto.rickandmortycompose.util.isMediumScreen
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi

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

    val bottomSheetState =
        rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val coroutineScope = rememberCoroutineScope()


    BottomSheetScaffold(
        topBar = {
            RickAndMortyTopBar(
                topBarText = currentDestination.getLabelResId(),
                isHaveFilterIcon = currentDestination.isCharacterScreen(),
                onClickFilterIcon = {
                    coroutineScope.launch {
                        if (bottomSheetState.isCollapsed) {
                            bottomSheetState.expand()
                        } else {
                            bottomSheetState.collapse()
                        }
                    }
                },
                isHaveNavigateIcon = currentDestination.isHaveNavigationIcon(),
                onClickNavigateIcon = {
                    navController.popBackStack()
                }
            )
        },
        sheetPeekHeight = SHEET_PEEK_HEIGHT,
        scaffoldState = scaffoldState,
        sheetShape = Shapes.large,
        sheetContent = {
            BottomSheetContent()
        }
    ) {
        Column {
            if (widthSizeClass.isMediumScreen() || widthSizeClass.isExpandedScreen()) {
                Row {
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

                    NavGraph(
                        navController = navController,
                        widthSizeClass = widthSizeClass
                    )

                }

            }

            if (widthSizeClass.isCompactScreen()) {
                BottomNav(
                    bottomNavItems = bottomNavItems,
                    currentDestination = currentDestination,
                    icons = icons,
                    navController = navController,
                    widthSizeClass = widthSizeClass
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
}

fun NavDestination?.getLabelResId(): Int {
    return when (this?.route) {
        Screen.Character.route -> BottomNavItemData.Character.name
        Screen.Location.route -> BottomNavItemData.Location.name
        Screen.Episode.route -> BottomNavItemData.Episode.name
        Screen.CharacterDetail.route -> R.string.character_detail
        Screen.LocationDetail.route -> R.string.location_detail
        else -> {
            BottomNavItemData.Character.name
        }
    }
}

fun NavDestination?.isCharacterScreen(): Boolean {

    return (this?.route == Screen.Character.route)
}

fun NavDestination?.isHaveNavigationIcon(): Boolean {
    return (navigationIconWhoHaveIt.contains(this?.route))
}

val navigationIconWhoHaveIt = listOf(
    Screen.CharacterDetail.route,
    Screen.LocationDetail.route,
    Screen.EpisodeDetail.route
)



