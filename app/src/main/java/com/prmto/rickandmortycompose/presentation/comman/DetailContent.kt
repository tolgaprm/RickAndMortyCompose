package com.prmto.rickandmortycompose.presentation.comman

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.annotation.ExperimentalCoilApi
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.EpisodeDetail
import com.prmto.rickandmortycompose.domain.model.LocationDetail
import com.prmto.rickandmortycompose.presentation.components.CharacterItemForGridView
import com.prmto.rickandmortycompose.presentation.components.ErrorScreen
import com.prmto.rickandmortycompose.presentation.ui.theme.LARGE_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.SMALL_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.detailHeaderBackgroundColor
import kotlin.math.min

@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun DetailContent(
    widthSizeClass: WindowWidthSizeClass,
    locationHeader: LocationDetail? = null,
    episodeHeader: EpisodeDetail? = null,
    characters: List<Character>,
    isLoading: Boolean,
    isError: Boolean,
    onClickCharacterItem: (characterId: Int) -> Unit,
    onRetryClick: () -> Unit
) {

    if (isError) {
        ErrorScreen(
            onRetryClick = onRetryClick
        )
    } else if (isLoading) {
        Column {

            DetailHeader(
                widthSizeClass = widthSizeClass,
                locationHeader = locationHeader,
                headerHeight = 130.dp
            )
            CharacterItemShimmerEffect()
        }

    } else {
        Column {

            val lazyGridState = rememberLazyGridState()

            val offset = remember {
                derivedStateOf {
                    min(
                        1f,
                        1 - (lazyGridState.firstVisibleItemScrollOffset / 600f + lazyGridState.firstVisibleItemIndex)
                    )
                }
            }


            val size by animateDpAsState(targetValue = max(0.dp, 130.dp * offset.value))
            DetailHeader(
                widthSizeClass = widthSizeClass,
                locationHeader = locationHeader,
                headerHeight = size
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
                horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING),
                contentPadding = PaddingValues(LARGE_PADDING),
                state = lazyGridState
            ) {
                items(characters) {
                    CharacterItemForGridView(
                        character = it,
                        widthSizeClass = widthSizeClass,
                        onClick = onClickCharacterItem
                    )
                }
            }

        }

    }
}

@ExperimentalUnitApi
@Composable
fun DetailHeader(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    locationHeader: LocationDetail? = null,
    episodeHeader: EpisodeDetail? = null,
    headerHeight: Dp
) {

    if (locationHeader != null) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(headerHeight),
            color = MaterialTheme.colors.detailHeaderBackgroundColor
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = locationHeader.type,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    )
                }

                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Text(
                    text = locationHeader.name,
                    style = MaterialTheme.typography.h4,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    } else if (episodeHeader != null) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp),
            color = MaterialTheme.colors.detailHeaderBackgroundColor
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = episodeHeader.air_date,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    )
                }
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Text(
                    text = episodeHeader.name,
                    style = MaterialTheme.typography.h4,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = episodeHeader.episode,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    )
                }
            }
        }
    }

}