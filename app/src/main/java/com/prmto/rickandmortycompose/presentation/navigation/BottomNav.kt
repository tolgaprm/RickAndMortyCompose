package com.prmto.rickandmortycompose.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.navigation.NavGraph
import com.prmto.rickandmortycompose.presentation.ui.theme.BOTTOM_NAV_ELEVATION
import com.prmto.rickandmortycompose.presentation.ui.theme.bottomNavSelectedColor
import com.prmto.rickandmortycompose.presentation.ui.theme.bottomNavUnSelectedColor
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_ICON_INDEX
import com.prmto.rickandmortycompose.util.Constant.EPISODE_ICON_INDEX
import com.prmto.rickandmortycompose.util.Constant.LOCATION_ICON_INDEX

@OptIn(ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun BottomNav(
    bottomNavItems: List<BottomNavItemData>,
    currentDestination: NavDestination?,
    icons: SnapshotStateList<Int>,
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    navigateToRoute: (route: String) -> Unit,

    ) {

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
                                        contentDescription = currentDestination.route
                                    )
                                },
                                selected = currentDestination.hierarchy.any { it.route == bottomNavItem.route },
                                onClick = {
                                    navigateToRoute(bottomNavItem.route)
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

        },
        content = {
            NavGraph(navController = navController, widthSizeClass = widthSizeClass)
        }
    )
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



