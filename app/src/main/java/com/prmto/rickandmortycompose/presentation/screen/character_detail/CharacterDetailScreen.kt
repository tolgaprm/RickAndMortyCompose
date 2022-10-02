package com.prmto.rickandmortycompose.presentation.screen.character_detail

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.presentation.comman.ErrorScreen

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onClickLocationItem: (locationId: Int) -> Unit,
    onClickEpisodeItem: (episodeId: Int) -> Unit
) {
    val state = viewModel.characterDetailState.collectAsState().value


    if (state.isError) {
        ErrorScreen(onRetryClick = {
            viewModel.onClickRetry()
        })
    } else {
        CharacterDetailContent(
            characterDetail = state.character,
            episodes = state.episodes,
            widthSizeClass = widthSizeClass,
            isLoading = state.isLoading,
            onClickLocationItem = onClickLocationItem,
            onClickEpisodeItem = onClickEpisodeItem
        )
    }


}
