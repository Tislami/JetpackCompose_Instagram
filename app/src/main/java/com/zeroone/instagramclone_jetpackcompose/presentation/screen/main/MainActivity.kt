package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.theme.InstagramClone_JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberAnimatedNavController()

            InstagramClone_JetpackComposeTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {

                    Main(navHostController = navHostController)
                }
            }
        }
    }
}
