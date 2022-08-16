package com.prmto.rickandmortycompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.presentation.ui.theme.*

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterListContent(
    characters: LazyPagingItems<Character>,
    listType: ListType
) {
    if (listType == ListType.LIST){
        LazyColumn() {
            items(characters) {
                it.let {
                    CharacterItem(character = it!!, onClick = {})
                }
            }
        }
    }

}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (characterId: Int) -> Unit
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

        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.character_image),
            modifier = Modifier
                .size(
                    IMAGE_LIST_TYPE_SIZE
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
                fontSize = MaterialTheme.typography.caption.fontSize
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Text(
                text = character.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${character.gender} - ${character.species}",
                    style = MaterialTheme.typography.caption,
                    fontSize = MaterialTheme.typography.caption.fontSize
                )

            }
        }

    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
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
        ), onClick = {}
    )
}