package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.presentation.components.CharacterListContent
import com.prmto.rickandmortycompose.presentation.ui.theme.ICON_SIZE
import com.prmto.rickandmortycompose.presentation.ui.theme.IconTintColor
import com.prmto.rickandmortycompose.presentation.ui.theme.SMALL_PADDING

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


    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            swipeRefreshState.isRefreshing = true
            characters.refresh()
            swipeRefreshState.isRefreshing = false
        },
    ) {
        CharacterListContent(
            characters = characters,
            listType = characterViewModel.stateListType.value,
            widthSizeClass = widthSizeClass,
            onClickCharacterItem = onClickCharacterItem,
            listTypeIconId = characterViewModel.listTypeIcon.value,
            onClickListTypeIcon = {
                characterViewModel.onClickListTypeIcon()
            }
        )
    }


}

@Composable
fun CharacterScreenHeader(
    @DrawableRes listTypeIconId: Int,
    onClickListTypeIcon: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                modifier = Modifier.padding(start = SMALL_PADDING),
                text = stringResource(R.string.list_type),
                style = MaterialTheme.typography.h6,
                fontSize = MaterialTheme.typography.h6.fontSize
            )

            IconButton(onClick = onClickListTypeIcon) {
                Icon(
                    modifier = Modifier.size(ICON_SIZE),
                    painter = painterResource(id = listTypeIconId),
                    contentDescription = stringResource(
                        id = R.string.list_type
                    ),
                    tint = MaterialTheme.colors.IconTintColor
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CharacterScreenHeaderPreview() {
    CharacterScreenHeader(listTypeIconId = R.drawable.ic_filter_list) {

    }
}

@Preview(showBackground = true)
@Composable
fun CharacterScreenHeaderWithGridListPreview() {
    CharacterScreenHeader(listTypeIconId = R.drawable.grid_list) {

    }
}
