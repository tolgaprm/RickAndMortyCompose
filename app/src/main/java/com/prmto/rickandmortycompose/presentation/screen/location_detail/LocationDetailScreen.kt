package com.prmto.rickandmortycompose.presentation.screen.location_detail

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.presentation.comman.DetailContent

@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun LocationDetailScreen(
    viewModel: LocationDetailViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onClickCharacterItem: (characterId: Int) -> Unit
) {
    val state = viewModel.state.value

    DetailContent(
        widthSizeClass = widthSizeClass,
        locationHeader = state.locationDetail,
        characters = state.characterList,
        isLoading = state.isLoading,
        isError = state.isError,
        onRetryClick = {
            viewModel.onRetryClick()
        },
        onClickCharacterItem = onClickCharacterItem
    )
}