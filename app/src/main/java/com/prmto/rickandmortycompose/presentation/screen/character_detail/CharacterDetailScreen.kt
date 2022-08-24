package com.prmto.rickandmortycompose.presentation.screen.character_detail

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi

@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass
) {
    val state = viewModel.characterDetailState.value


    CharacterDetailContent(
        characterDetail = state.character,
        episodes = state.episodes,
        widthSizeClass = widthSizeClass,
        onClickLocationItem = { locationId ->
            Log.d("CharacterDetail", locationId.toString())
        },
        onClickEpisodeItem = { episodeId ->
            Log.d("CharacterDetail", episodeId.toString())
        }
    )


}
