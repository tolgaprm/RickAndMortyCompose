package com.prmto.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.prmto.rickandmortycompose.presentation.RickAndMortyApp
import com.prmto.rickandmortycompose.presentation.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
@ExperimentalUnitApi
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            val navHostController = rememberNavController()
            RickAndMortyComposeTheme {
                val widthSizeClasses = calculateWindowSizeClass(this).widthSizeClass
                RickAndMortyApp(
                    navController = navHostController,
                    widthSizeClass = widthSizeClasses
                )
            }
        }
    }
}

