package com.prmto.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.prmto.rickandmortycompose.presentation.bottom_navigation.BottomNav
import com.prmto.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

@ExperimentalUnitApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            val navHostController = rememberNavController()
            RickAndMortyComposeTheme {
               BottomNav(navController = navHostController)
            }
        }
    }
}

