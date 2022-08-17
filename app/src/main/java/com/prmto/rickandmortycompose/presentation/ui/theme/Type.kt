package com.prmto.rickandmortycompose.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.prmto.rickandmortycompose.util.isCompactScreen

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

@ExperimentalUnitApi
@Composable
fun Typography.captionSize(widthSizeClass: WindowWidthSizeClass): TextUnit {
    return if (widthSizeClass.isCompactScreen()) {
        TextUnit(value = MaterialTheme.typography.caption.fontSize.value, type = TextUnitType.Sp)
    } else {
        TextUnit(value = 16.0f, type = TextUnitType.Sp)
    }
}

@ExperimentalUnitApi
@Composable
fun Typography.body1Size(widthSizeClass: WindowWidthSizeClass): TextUnit {
    return if (widthSizeClass.isCompactScreen()) {
        TextUnit(value = MaterialTheme.typography.body1.fontSize.value, type = TextUnitType.Sp)
    } else {
        TextUnit(value = 16.0f, type = TextUnitType.Sp)
    }
}
