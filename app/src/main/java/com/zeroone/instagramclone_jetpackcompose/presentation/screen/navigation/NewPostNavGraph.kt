package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost.AddScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost.NewPostScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.newPostNavGraph(appState: AppState) {
    navigation(
        route = Graph.NEW_POST,
        startDestination = Add.Gallery.route,
    ) {
        composable(route = Add.Gallery.route) {
            AddScreen(appState = appState)
        }
        composable(route = Add.NewPostScreen.route) {
            NewPostScreen(appState =appState)
        }
    }
}

sealed class Add(val route: String) {
    object Gallery : Add(route = "add")
    object NewPostScreen : Add(route = "newPost")
}