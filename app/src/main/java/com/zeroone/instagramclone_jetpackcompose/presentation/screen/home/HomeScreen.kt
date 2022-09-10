package com.zeroone.instagramclone_jetpackcompose.presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.TransformOrigin
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultPost
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.HomeTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard

@Composable
fun HomeScreen(
    appState: AppState,
    ) {
    val navController by remember { mutableStateOf(appState.navHostController) }

    Scaffold(
        topBar = { HomeTopBar()},
        content = {
            Content(size = 100)
        },
    )
}
@Composable
private fun Content(size: Int) {

    LazyColumn {
        items(size) {
            PostCard(post = defaultPost,{})
        }
    }
}