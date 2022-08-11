package com.prmto.rickandmortycompose.presentation.bottom_navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prmto.rickandmortycompose.navigation.NavGraph
import com.prmto.rickandmortycompose.ui.theme.BOTTOM_NAV_ELEVATION
import com.prmto.rickandmortycompose.ui.theme.bottomNavSelectedColor
import com.prmto.rickandmortycompose.ui.theme.bottomNavUnSelectedColor
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_ICON_INDEX
import com.prmto.rickandmortycompose.util.Constant.EPISODE_ICON_INDEX
import com.prmto.rickandmortycompose.util.Constant.LOCATION_ICON_INDEX

@ExperimentalUnitApi
@Composable
fun BottomNav(
    navController: NavHostController
) {
    val bottomNavItems = listOf(
        BottomNavItemData.Character,
        BottomNavItemData.Location,
        BottomNavItemData.Episode
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val icons = remember {
        mutableStateListOf(
            BottomNavItemData.Character.selectedIcon,
            BottomNavItemData.Location.unSelectedIcon,
            BottomNavItemData.Episode.unSelectedIcon,
        )
    }


    Scaffold(
        bottomBar = {
            bottomNavItems.forEach { it ->
                if (currentDestination?.route == it.route) {
                    BottomNavigation(
                        backgroundColor = Color.White,
                        elevation = BOTTOM_NAV_ELEVATION
                    ) {
                        bottomNavItems.forEachIndexed { index, bottomNavItem ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = icons[index]),
                                        contentDescription = null
                                    )
                                },
                                selected = currentDestination.hierarchy.any { it.route == bottomNavItem.route },
                                onClick = {
                                    navController.navigate(route = bottomNavItem.route)
                                    handleIconsState(bottomNavItem = bottomNavItem, icons = icons)
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = bottomNavItem.name),
                                        style = MaterialTheme.typography.caption,
                                        letterSpacing = TextUnit(
                                            value = 1.3f,
                                            type = TextUnitType.Sp
                                        )
                                    )
                                },
                                selectedContentColor = MaterialTheme.colors.bottomNavSelectedColor,
                                unselectedContentColor = MaterialTheme.colors.bottomNavUnSelectedColor
                            )
                        }
                    }
                }
            }

        }
    ) {
        NavGraph(navController = navController)
    }
}

fun handleIconsState(bottomNavItem: BottomNavItemData, icons: SnapshotStateList<Int>) {
    val character = BottomNavItemData.Character
    val location = BottomNavItemData.Location
    val episode = BottomNavItemData.Episode

    if (bottomNavItem.route == character.route) {
        icons[CHARACTER_ICON_INDEX] = character.selectedIcon
    } else {
        icons[CHARACTER_ICON_INDEX] = character.unSelectedIcon
    }
    if (bottomNavItem.route == location.route) {
        icons[LOCATION_ICON_INDEX] = location.selectedIcon
    } else {
        icons[LOCATION_ICON_INDEX] = location.unSelectedIcon
    }
    if (bottomNavItem.route == episode.route) {
        icons[EPISODE_ICON_INDEX] = episode.selectedIcon
    } else {
        icons[EPISODE_ICON_INDEX] = episode.unSelectedIcon
    }
}



