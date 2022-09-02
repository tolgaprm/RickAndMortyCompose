package com.prmto.rickandmortycompose.presentation.screen.episode

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.prmto.rickandmortycompose.presentation.components.EpisodeListContent

@Composable
fun EpisodeScreen(
    episodeViewModel: EpisodeViewModel = hiltViewModel(),
    onClickEpisodeItem: (episodeId: Int) -> Unit

) {
    val episodes = episodeViewModel.episodes.collectAsLazyPagingItems()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            swipeRefreshState.isRefreshing = true
            episodes.refresh()
            swipeRefreshState.isRefreshing = false
        }
    ) {
        EpisodeListContent(
            episodes = episodes,
            onClickEpisodeItem = onClickEpisodeItem
        )
    }

}