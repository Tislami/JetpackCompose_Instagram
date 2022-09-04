package com.zeroone.instagramclone_jetpackcompose.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultPost
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.HomeTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard

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
            PostCard(post = defaultPost)
        }
    }
}