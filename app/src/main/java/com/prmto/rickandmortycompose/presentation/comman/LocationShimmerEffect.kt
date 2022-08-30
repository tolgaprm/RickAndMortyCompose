package com.prmto.rickandmortycompose.presentation.comman

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prmto.rickandmortycompose.presentation.ui.theme.BOTTOM_NAV_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.LARGE_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.Shapes
import com.prmto.rickandmortycompose.presentation.ui.theme.shimmerEffectColor
import com.prmto.rickandmortycompose.util.Constant.LOCATION_LIST_GRID_CELL
import com.prmto.rickandmortycompose.util.calculateAlphaInfiniteAnimation

@Composable
fun LocationShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(LOCATION_LIST_GRID_CELL),
        contentPadding = PaddingValues(
            top = LARGE_PADDING,
            start = LARGE_PADDING,
            end = LARGE_PADDING
        ),
        verticalArrangement = Arrangement.spacedBy(LARGE_PADDING),
        horizontalArrangement = Arrangement.spacedBy(LARGE_PADDING)
    ) {
        items(16) {
            AnimatedLocationShimmerEffect()
        }
    }
}

@Composable
fun AnimatedLocationShimmerEffect() {
    val alphaAnim = calculateAlphaInfiniteAnimation()

    LocationShimmerEffectItem(alpha = alphaAnim)
}

@Composable
fun LocationShimmerEffectItem(alpha: Float) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(Shapes.large)
            .alpha(alpha)
            .border(
                1.dp,
                color = MaterialTheme.colors.shimmerEffectColor,
                shape = Shapes.large,
            ),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LARGE_PADDING)
        ) {
            Surface(
                modifier = Modifier
                    .height(10.dp)
                    .width(100.dp)
                    .alpha(alpha),
                color = MaterialTheme.colors.shimmerEffectColor
            ) {
            }

            Spacer(modifier = Modifier.height(LARGE_PADDING))
            Surface(
                modifier = Modifier
                    .height(10.dp)
                    .width(150.dp)
                    .alpha(alpha),
                color = MaterialTheme.colors.shimmerEffectColor
            ) {
            }
        }

    }
}

@Preview
@Composable
fun LocationShimmerEffectItemPreview() {
    LocationShimmerEffectItem(alpha = 0.4f)
}

