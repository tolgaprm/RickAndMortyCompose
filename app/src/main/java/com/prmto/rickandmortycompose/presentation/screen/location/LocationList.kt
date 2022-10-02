package com.prmto.rickandmortycompose.presentation.screen.location

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.navigation.Screen
import com.prmto.rickandmortycompose.presentation.ui.theme.*
import com.prmto.rickandmortycompose.util.Constant
import com.prmto.rickandmortycompose.util.MultiDevicePreview
import com.prmto.rickandmortycompose.util.handleLoadState

@ExperimentalUnitApi
@Composable
fun LocationList(
    modifier: Modifier = Modifier,
    locations: LazyPagingItems<Location>,
    widthSizeClass: WindowWidthSizeClass,
    onClickItem: (Int) -> Unit,

    ) {

    val result = handleLoadState(
        loadState = locations.loadState,
        onRetryClick = { locations.retry() },
        whichScreen = Screen.Location,
        isEmptyList = locations.itemCount == 0
    )

    if (result) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(Constant.LOCATION_LIST_GRID_CELL),
            contentPadding = PaddingValues(
                top = LARGE_PADDING,
                start = LARGE_PADDING,
                end = LARGE_PADDING,
                bottom = BOTTOM_NAV_PADDING
            ),
            verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
            horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING)
        ) {
            items(locations.itemCount) { index ->
                val location = locations[index]
                location?.let {
                    LocationItem(
                        location = it,
                        widthSizeClass = widthSizeClass,
                        onClickItem = onClickItem
                    )
                }
            }
        }
    }

}


@ExperimentalUnitApi
@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    widthSizeClass: WindowWidthSizeClass,
    onClickItem: (Int) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.large)
            .border(
                2.dp,
                color = MaterialTheme.colors.itemBorderColor,
                shape = Shapes.large
            )
            .clickable {
                onClickItem(location.id)
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(LARGE_PADDING),
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = location.type,
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.captionSize(widthSizeClass = widthSizeClass)
                )
            }

            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Text(
                text = location.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1Size(widthSizeClass = widthSizeClass),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@ExperimentalUnitApi
@MultiDevicePreview
@Composable
fun LocationItemPreview() {

    LazyVerticalGrid(
        columns = GridCells.Fixed(Constant.LOCATION_LIST_GRID_CELL),
        contentPadding = PaddingValues(
            top = LARGE_PADDING,
            start = LARGE_PADDING,
            end = LARGE_PADDING,
            bottom = BOTTOM_NAV_PADDING
        ),
        verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
        horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING)
    ){
        items(10){
            LocationItem(
                location = Location(
                    id = 1,
                    type = "Planet",
                    name = "Earth (C-137)"

                ), widthSizeClass = WindowWidthSizeClass.Compact,
                onClickItem = {}
            )
        }
    }

}