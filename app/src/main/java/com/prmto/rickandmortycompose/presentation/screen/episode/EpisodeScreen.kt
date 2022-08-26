package com.prmto.rickandmortycompose.presentation.screen.episode

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.prmto.rickandmortycompose.presentation.components.EpisodeListContent

@Composable
fun EpisodeScreen(
    episodeViewModel: EpisodeViewModel = hiltViewModel(),
    onClickEpisodeItem: (episodeId: Int) -> Unit

) {
    val episodes = episodeViewModel.episodes.collectAsLazyPagingItems()

    EpisodeListContent(
        episodes = episodes,
        onClickEpisodeItem = onClickEpisodeItem
    )
}