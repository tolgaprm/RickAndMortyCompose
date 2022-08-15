package com.prmto.rickandmortycompose.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.presentation.ui.theme.IconTintColor
import com.prmto.rickandmortycompose.presentation.ui.theme.TOP_BAR_ELEVATION

@ExperimentalUnitApi
@Composable
fun RickAndMortyTopBar(
    modifier: Modifier = Modifier,
    @StringRes topBarText: Int,
    isHaveNavigateIcon: Boolean? = null,
    onClickNavigateIcon: (() -> Unit)? = null,
    isHaveFilterIcon: Boolean? = null,
    onClickFilterIcon: (() -> Unit)? = null
) {

    TopAppBar(
        modifier = modifier,
        title = { TopAppBarText(topBarText = topBarText) },
        backgroundColor = Color.White,
        navigationIcon = {
            if ((isHaveNavigateIcon != null) && isHaveNavigateIcon) {
                if (onClickNavigateIcon != null) {
                    IconButton(
                        onClick = onClickNavigateIcon
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.up_button),
                            tint = MaterialTheme.colors.IconTintColor
                        )
                    }
                }
            }
        },
        actions = {
            if (isHaveFilterIcon != null && isHaveFilterIcon) {
                if (onClickFilterIcon != null) {
                    IconButton(onClick = onClickFilterIcon) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter_list),
                            contentDescription = stringResource(R.string.filter_list),
                            tint = MaterialTheme.colors.IconTintColor
                        )
                    }
                }

            }

        },
        elevation = TOP_BAR_ELEVATION
    )

}

@ExperimentalUnitApi
@Composable
fun TopAppBarText(
    @StringRes topBarText: Int
) {
    Text(
        text = stringResource(id = topBarText),
        style = MaterialTheme.typography.h6,
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        letterSpacing = TextUnit(value = 0.5f, type = TextUnitType.Sp)
    )
}

@ExperimentalUnitApi
@Preview
@Composable
fun RickAndMortyTopBarPreview() {
    RickAndMortyTopBar(topBarText = R.string.character)
}

@ExperimentalUnitApi
@Preview
@Composable
fun RickAndMortyTopBarWithUpButtonPreview() {
    RickAndMortyTopBar(
        topBarText =  R.string.character,
        isHaveNavigateIcon = true,
        onClickNavigateIcon = {}
    )
}

@ExperimentalUnitApi
@Preview
@Composable
fun RickAndMortyTopBarWithUpButtonAndFilterIconPreview() {
    RickAndMortyTopBar(
        topBarText =  R.string.character,
        isHaveNavigateIcon = true,
        onClickNavigateIcon = {},
        isHaveFilterIcon = true,
        onClickFilterIcon = {}
    )
}