package com.zeroone.instagramclone_jetpackcompose.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.HomeTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState

@Composable
fun HomeScreen(
    appState: AppState,
    ) {
    val navController by remember { mutableStateOf(appState.navHostController) }

    Scaffold(
        topBar = { HomeTopBar()},
        content = {
            Content(size = 10)
        },
    )
}

@Composable
private fun Content(size: Int) {
    LazyColumn {
        items(size) {
           // PostCard(post = defaultPost)
        }
    }
}