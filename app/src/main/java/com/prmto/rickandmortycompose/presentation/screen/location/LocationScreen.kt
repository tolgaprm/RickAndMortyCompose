package com.prmto.rickandmortycompose.presentation.screen.location

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@ExperimentalUnitApi
@Composable
fun LocationScreen(
    locationViewModel: LocationViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass
) {
    val locations = locationViewModel.locations.collectAsLazyPagingItems()

    LocationList(
        locations = locations,
        widthSizeClass = widthSizeClass,
        onClickItem = {

        }
    )
}