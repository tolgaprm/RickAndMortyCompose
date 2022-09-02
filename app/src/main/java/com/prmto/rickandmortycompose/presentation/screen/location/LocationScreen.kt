package com.prmto.rickandmortycompose.presentation.screen.location

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalUnitApi
@Composable
fun LocationScreen(
    locationViewModel: LocationViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onNavigateToLocationScreen: (locationId: Int) -> Unit
) {
    val locations = locationViewModel.locations.collectAsLazyPagingItems()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            swipeRefreshState.isRefreshing = true
            locations.refresh()
            swipeRefreshState.isRefreshing = false
        }
    ) {
        LocationList(
            locations = locations,
            widthSizeClass = widthSizeClass,
            onClickItem = onNavigateToLocationScreen
        )
    }


}