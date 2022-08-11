package com.prmto.rickandmortycompose.presentation.screen.character

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prmto.rickandmortycompose.navigation.Screen

@Composable
fun CharacterScreen(navController: NavHostController) {

Text(text = "Character Screen")
}

