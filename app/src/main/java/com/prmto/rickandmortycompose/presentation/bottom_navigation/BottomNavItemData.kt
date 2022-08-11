package com.prmto.rickandmortycompose.presentation.bottom_navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.navigation.Screen

sealed class BottomNavItemData(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int,
    @StringRes val name: Int,
    val route: String
) {

    object Character : BottomNavItemData(
        selectedIcon = R.drawable.ic_character_selected,
        unSelectedIcon = R.drawable.ic_character,
        name = R.string.character,
        route = Screen.Character.route
    )


    object Location : BottomNavItemData(
        selectedIcon = R.drawable.ic_location_selected,
        unSelectedIcon = R.drawable.ic_location,
        name = R.string.location,
        route = Screen.Location.route
    )

    object Episode : BottomNavItemData(
        selectedIcon = R.drawable.ic_episode_selected,
        unSelectedIcon = R.drawable.ic_episode,
        name = R.string.episode,
        route = Screen.Episode.route
    )


}
