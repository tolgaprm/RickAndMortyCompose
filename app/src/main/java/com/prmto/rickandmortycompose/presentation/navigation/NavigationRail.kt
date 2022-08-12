package com.prmto.rickandmortycompose.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.presentation.ui.theme.SMALL_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.Shapes

@ExperimentalUnitApi
@Composable
fun NavRail(
    bottomNavItems: List<BottomNavItemData>,
    currentDestination: NavDestination?,
    icons: SnapshotStateList<Int>,
    onHeaderClick: () -> Unit,
    navigateToRoute: (route: String) -> Unit
) {

    NavigationRail(
        header = {
            NavRailHeader(
                onHeaderClick = onHeaderClick
            )
        }
    ) {
        currentDestination?.let {
            bottomNavItems.forEachIndexed { index, bottomNav ->
                NavigationRailItem(
                    selected = currentDestination.hierarchy.any { it.route == bottomNav.route },
                    onClick = {
                        navigateToRoute(bottomNav.route)
                        handleIconsState(bottomNavItem = bottomNav, icons = icons)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = bottomNav.route
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = bottomNav.name),
                            style = MaterialTheme.typography.caption,
                            letterSpacing = TextUnit(
                                value = 1.3f,
                                type = TextUnitType.Sp
                            )
                        )
                    }
                )
            }
        }

    }
}

@Composable
fun NavRailHeader(
    onHeaderClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .padding(SMALL_PADDING)
            .size(60.dp)
            .clip(Shapes.large)
            .clickable {
                onHeaderClick()
            },
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.app_logo),
        contentScale = ContentScale.Crop
    )
}