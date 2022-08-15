package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.presentation.components.RickAndMortyTopBar
import com.prmto.rickandmortycompose.presentation.ui.theme.LARGE_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.SHEET_PEEK_HEIGHT
import com.prmto.rickandmortycompose.presentation.ui.theme.TOP_BAR_ELEVATION
import kotlinx.coroutines.launch

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    val characters = characterViewModel.getAllHeroes.collectAsLazyPagingItems()




}



