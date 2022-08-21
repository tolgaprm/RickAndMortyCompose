package com.prmto.rickandmortycompose.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Color.Companion.BasicBlack get() = Color(0xFF081F32)
val Indigo = Color(0xFF5856D6)
val Gray1 = Color(0xFF8E8E93)
val Gray6 = Color(0xFFF2F2F7)
val Gray5 = Color(0xFFE5E5EA)

val Colors.bottomNavSelectedColor
    @Composable
    get() = Indigo

val Colors.bottomNavUnSelectedColor
    @Composable
    get() = Gray1

val Colors.IconTintColor
    @Composable
    get() = Indigo

val Colors.buttonTextColor
    @Composable
    get() = Gray6

val Colors.itemBorderColor
    @Composable
    get() = Gray5

fun Color.Companion.statusTextColor(
    statusName: String
): Color {
    return when (statusName) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> {
            Color.Gray
        }
    }
}

