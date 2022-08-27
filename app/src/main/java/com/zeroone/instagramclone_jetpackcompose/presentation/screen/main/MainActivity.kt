package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.Login
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login.LoginScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.theme.InstagramClone_JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            Main(navController = navController)
        }
    }
}

@Composable
fun Main(navController: NavController) {
    InstagramClone_JetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoginScreen()
        }
    }
}