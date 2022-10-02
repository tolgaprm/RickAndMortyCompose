package com.prmto.rickandmortycompose.presentation.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.presentation.screen.character.CharacterScreenHeader
import com.prmto.rickandmortycompose.presentation.ui.theme.*
import com.prmto.rickandmortycompose.util.MultiDevicePreviewWithOutSystemUI
import com.prmto.rickandmortycompose.util.handleLoadState
import com.prmto.rickandmortycompose.util.isExpandedScreen
import com.prmto.rickandmortycompose.util.isMediumScreen

@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterListContent(
    characters: LazyPagingItems<Character>,
    listType: ListType,
    widthSizeClass: WindowWidthSizeClass,
    @DrawableRes listTypeIconId: Int,
    onClickListTypeIcon: () -> Unit,
    onClickCharacterItem: (Int) -> Unit,

    ) {

    val result = handleLoadState(
        loadState = characters.loadState,
        listType = listType,
        onRetryClick = { characters.retry() },
        isEmptyList = characters.itemCount == 0
    )

    if (listType == ListType.LIST) {

        if (result) {
            LazyColumn(
                contentPadding = PaddingValues(SMALL_PADDING),
            ) {

                item(
                ) {
                    CharacterScreenHeader(
                        listTypeIconId = listTypeIconId,
                        onClickListTypeIcon = onClickListTypeIcon
                    )
                }
                items(
                    characters,
                    key = { item: Character -> item.id }
                ) {
                    it?.let {
                        CharacterItem(
                            character = it,
                            widthSizeClass = widthSizeClass,
                            onClick = {
                                onClickCharacterItem(it)
                            }
                        )

                        Divider(
                            modifier = Modifier.padding(horizontal = MEDIUM_PADDING)
                        )
                    }
                }
            }
        }

    } else {

        if (result) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
                horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING),
                contentPadding = PaddingValues(
                    top = LARGE_PADDING,
                    start = LARGE_PADDING,
                    end = LARGE_PADDING,
                    bottom = BOTTOM_NAV_PADDING
                )
            ) {

                item(
                    span = {
                        GridItemSpan(maxCurrentLineSpan)
                    }
                ) {
                    CharacterScreenHeader(
                        listTypeIconId = listTypeIconId,
                        onClickListTypeIcon = onClickListTypeIcon
                    )
                }
                items(
                    characters.itemCount,
                ) { index ->
                    characters[index]?.let { it ->
                        CharacterItemForGridView(
                            character = it,
                            widthSizeClass = widthSizeClass
                        ) {
                            onClickCharacterItem(it)
                        }
                    }
                }
            }
        }


    }
}


@ExperimentalUnitApi
@ExperimentalCoilApi
@Composable
fun CharacterItemForGridView(
    modifier: Modifier = Modifier,
    character: Character,
    widthSizeClass: WindowWidthSizeClass,
    onClick: (characterId: Int) -> Unit,
) {

    val request = ImageRequest.Builder(
        context = LocalContext.current
    )
        .crossfade(300)
        .data(character.image)
        .error(R.drawable.error_image)
        .build()


    val painter = rememberImagePainter(
        request = request
    )


    Card(
        modifier = Modifier
            .clip(Shapes.large)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(2.dp, color = MaterialTheme.colors.itemBorderColor, shape = Shapes.large)
            .clickable {
                onClick(character.id)
            },
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth(),
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.character_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 140.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 12.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Card(
                    modifier = Modifier
                        .padding(start = MEDIUM_PADDING, top = LARGE_PADDING)
                        .size(10.dp)
                        .clip(CircleShape),
                    backgroundColor = Color.statusTextColor(character.status)
                ) {

                }

                Text(
                    modifier = Modifier.padding(start = SMALL_PADDING, top = 12.dp),
                    text = character.status,
                    color = Color.Gray,
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
                )
            }

            Text(
                modifier = Modifier.padding(MEDIUM_PADDING),
                text = character.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1Size(widthSizeClass = widthSizeClass),
                fontWeight = FontWeight.Bold
            )
        }

    }
}


@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    widthSizeClass: WindowWidthSizeClass,
    onClick: (characterId: Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING)
            .clip(Shapes.medium)
            .clickable {
                onClick(character.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {


        val request = ImageRequest.Builder(
            context = LocalContext.current
        )
            .crossfade(300)
            .data(character.image)
            .error(R.drawable.error_image)
            .build()


        val painter = rememberImagePainter(
            request = request
        )

        val imageSize =
            if (widthSizeClass.isMediumScreen() || widthSizeClass.isExpandedScreen()) IMAGE_LIST_TYPE_SIZE_MEDIUM_SCREEN else IMAGE_LIST_TYPE_SIZE_COMPACT_SCREEN

        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.character_image),
            modifier = Modifier
                .size(
                    imageSize
                )
                .padding(SMALL_PADDING)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SMALL_PADDING)

        ) {

            Text(
                text = character.status,
                color = Color.statusTextColor(character.status),
                style = MaterialTheme.typography.caption,
                fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
            )

            Log.d("captionSize", MaterialTheme.typography.body1.fontSize.value.toString())

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Text(
                text = character.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1Size(widthSizeClass = widthSizeClass),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${character.gender} - ${character.species}",
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
                )

            }
        }

    }
}


@ExperimentalUnitApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@MultiDevicePreviewWithOutSystemUI
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            image = "",
            gender = "Male",
            species = "Human"
        ), onClick = {},
        widthSizeClass = WindowWidthSizeClass.Compact
    )
}


