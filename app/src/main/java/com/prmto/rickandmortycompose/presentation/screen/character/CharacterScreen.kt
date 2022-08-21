package com.prmto.rickandmortycompose.presentation.screen.character

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.presentation.components.CharacterListContent
import com.prmto.rickandmortycompose.presentation.ui.theme.ICON_SIZE
import com.prmto.rickandmortycompose.presentation.ui.theme.IconTintColor
import com.prmto.rickandmortycompose.presentation.ui.theme.SMALL_PADDING
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onClickCharacterItem: (Int) -> Unit
) {
    val characters = characterViewModel.getAllHeroes.collectAsLazyPagingItems()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                modifier = Modifier.padding(start = SMALL_PADDING),
                text = stringResource(R.string.list_type),
                style = MaterialTheme.typography.h6,
                fontSize = MaterialTheme.typography.h6.fontSize
            )

            IconButton(onClick = {
                characterViewModel.onClickListTypeIcon()
            }) {
                Icon(
                    modifier = Modifier.size(ICON_SIZE),
                    painter = painterResource(id = characterViewModel.listTypeIcon.value),
                    contentDescription = stringResource(
                        id = R.string.list_type
                    ),
                    tint = MaterialTheme.colors.IconTintColor
                )
            }
        }
    }

    CharacterListContent(
        characters = characters,
        listType = characterViewModel.stateListType.value,
        widthSizeClass = widthSizeClass,
        onClickCharacterItem = onClickCharacterItem
    )


}



