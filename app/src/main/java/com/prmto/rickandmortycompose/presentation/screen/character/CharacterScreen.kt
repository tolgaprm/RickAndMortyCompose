package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    val characters = characterViewModel.getAllHeroes.collectAsLazyPagingItems()

}

