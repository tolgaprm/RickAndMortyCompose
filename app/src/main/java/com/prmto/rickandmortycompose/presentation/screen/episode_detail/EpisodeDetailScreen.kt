package com.prmto.rickandmortycompose.presentation.screen.episode_detail

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.presentation.comman.DetailContent

@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun EpisodeDetailScreen(
    viewModel: EpisodeDetailViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onClickCharacterItem: (characterId: Int) -> Unit
) {
    val state = viewModel.state.value

    DetailContent(
        widthSizeClass = widthSizeClass,
        episodeHeader = state.episodeDetail,
        characters = state.characters,
        isLoading = state.isLoading,
        isError = state.isError,
        onRetryClick = {
            viewModel.onRetryClick()
        },
        onClickCharacterItem = onClickCharacterItem
    )
}