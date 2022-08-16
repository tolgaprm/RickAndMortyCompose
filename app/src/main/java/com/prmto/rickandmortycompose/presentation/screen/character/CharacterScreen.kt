package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.presentation.components.CharacterListContent

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    val characters = characterViewModel.getAllHeroes.collectAsLazyPagingItems()

    CharacterListContent(
        characters = characters,
        listType = ListType.LIST
    )


}



