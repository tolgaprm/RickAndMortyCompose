package com.prmto.rickandmortycompose.util

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@Composable
fun WindowWidthSizeClass.isCompactScreen(
): Boolean {
    return this == WindowWidthSizeClass.Compact
}

@Composable
fun WindowWidthSizeClass.isMediumScreen(
): Boolean {
    return this == WindowWidthSizeClass.Medium
}

@Composable
fun WindowWidthSizeClass.isExpandedScreen(
): Boolean {
    return this == WindowWidthSizeClass.Expanded
}