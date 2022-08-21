package com.prmto.rickandmortycompose.presentation.screen.episode

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.prmto.rickandmortycompose.domain.model.EpisodeListItem

@Composable
fun EpisodeScreen(
    episodeViewModel: EpisodeViewModel = hiltViewModel()
) {
    val episodes = episodeViewModel.episodes.collectAsLazyPagingItems()

    LazyColumn() {
        items(episodes) {
            it?.let {
                if (it is EpisodeListItem.SeparatorItem) {
                    Text(text = it.season)
                } else {
                    it as EpisodeListItem.EpisodeItem
                    Text(text = it.episode.episode)
                }

            }
        }
    }
}