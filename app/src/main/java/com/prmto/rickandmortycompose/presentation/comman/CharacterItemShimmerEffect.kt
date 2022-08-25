package com.prmto.rickandmortycompose.presentation.comman

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prmto.rickandmortycompose.presentation.ui.theme.*

@Composable
fun CharacterItemShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        contentPadding = PaddingValues(LARGE_PADDING),
        verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
        horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING)
    ) {
        items(15) {
            AnimatedCharacterShimmerItem()
        }
    }
}

@Composable
fun AnimatedCharacterShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 700,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    CharacterItemShimmerEffectContent(alpha = alphaAnim)
}

@Composable
fun CharacterItemShimmerEffectContent(
    alpha: Float
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()

            .alpha(alpha)
            .height(180.dp)
            .border(1.dp, color =  MaterialTheme.colors.shimmerEffectColor, Shapes.large),
        color = Color.White,
        shape = Shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .alpha(alpha),
                color =  MaterialTheme.colors.shimmerEffectColor
            ) {
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SMALL_PADDING)
            ) {
                Surface(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .alpha(alpha),
                    color =  MaterialTheme.colors.shimmerEffectColor
                ) {
                }
                Surface(
                    modifier = Modifier
                        .height(10.dp)
                        .padding(start = SMALL_PADDING)
                        .fillMaxWidth(0.6f)
                        .alpha(alpha),
                    color =  MaterialTheme.colors.shimmerEffectColor,
                    shape = Shapes.medium
                ) {
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING)
                    .height(15.dp)
                    .alpha(alpha),
                color =  MaterialTheme.colors.shimmerEffectColor,
                shape = Shapes.medium
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterShimmerItemPreview() {
    CharacterItemShimmerEffectContent(alpha = 0.4f)
}