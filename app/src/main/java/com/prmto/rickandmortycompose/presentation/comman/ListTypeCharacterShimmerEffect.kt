package com.prmto.rickandmortycompose.presentation.comman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prmto.rickandmortycompose.presentation.ui.theme.MEDIUM_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.Shapes
import com.prmto.rickandmortycompose.presentation.ui.theme.shimmerEffectColor
import com.prmto.rickandmortycompose.util.calculateAlphaInfiniteAnimation

@Composable
fun CharacterShimmerEffectListType() {

    LazyColumn() {
        items(10) {
            AnimatedCharacterShimmerEffectListType()
        }
    }

}

@Composable
fun AnimatedCharacterShimmerEffectListType() {

    val alphaAnim = calculateAlphaInfiniteAnimation()

    ListTypeCharacterShimmerEffectItem(alpha = alphaAnim)
}


@Composable
fun ListTypeCharacterShimmerEffectItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .alpha(alpha),
        shape = Shapes.large,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            Surface(
                modifier = Modifier
                    .padding(MEDIUM_PADDING)
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .alpha(alpha),
                color = MaterialTheme.colors.shimmerEffectColor
            ) {
            }
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center

            ) {
                Surface(
                    modifier = Modifier
                        .height(10.dp)
                        .width(100.dp)
                        .alpha(alpha),
                    shape = Shapes.large,
                    color = MaterialTheme.colors.shimmerEffectColor
                ) {
                }
                repeat(2) {
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    Surface(
                        modifier = Modifier
                            .height(10.dp)
                            .width(150.dp)
                            .alpha(alpha),
                        shape = Shapes.large,
                        color = MaterialTheme.colors.shimmerEffectColor
                    ) {
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListTypeCharacterShimmerEffectItemPreview() {
    ListTypeCharacterShimmerEffectItem(alpha = 0.5f)
}