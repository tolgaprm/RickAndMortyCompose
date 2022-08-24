package com.prmto.rickandmortycompose.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prmto.rickandmortycompose.presentation.ui.theme.LARGE_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.MEDIUM_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.SMALL_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.Shapes

@Composable
fun EpisodeItemShimmerEffect() {

    repeat(3){
        AnimatedEpisodeShimmerEffect()
    }


}

@Composable
fun AnimatedEpisodeShimmerEffect() {
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

    EpisodeItemShimmerEffectContent(alpha = alphaAnim)
}

@Composable
fun EpisodeItemShimmerEffectContent(
    alpha: Float
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LARGE_PADDING)
            .height(80.dp),
        color = Color.White,
        shape = Shapes.medium
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(MEDIUM_PADDING)
                    .weight(0.4f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth(0.5f)
                        .height(10.dp),
                    color = Gray,
                    shape = Shapes.medium
                ) {}
                Spacer(modifier = Modifier.padding(SMALL_PADDING))
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth(0.3f)
                        .height(15.dp),
                    color = Gray,
                    shape = Shapes.medium
                ) {}
                Spacer(modifier = Modifier.padding(SMALL_PADDING))
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth(0.7f)
                        .height(15.dp),
                    color = Gray,
                    shape = Shapes.medium
                ) {}
            }

            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .size(28.dp)
                    .clip(CircleShape),
                color = Gray
            ) {

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerEffectPreview() {
    EpisodeItemShimmerEffectContent(alpha = 0.4f)
}