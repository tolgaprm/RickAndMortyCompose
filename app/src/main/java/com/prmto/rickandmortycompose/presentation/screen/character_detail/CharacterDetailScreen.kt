package com.prmto.rickandmortycompose.presentation.screen.character_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.characterDetailState.value

    state.character?.let {
        Log.d("CharacterDetail", it.name)

    }

    state.episodes.forEach {
        Log.d("CharacterDetail", it.name)
    }
}