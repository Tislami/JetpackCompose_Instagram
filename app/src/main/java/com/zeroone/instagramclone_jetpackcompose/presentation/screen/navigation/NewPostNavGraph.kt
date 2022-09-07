package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

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
        startDestination = NewPostScreens.NewPostGallery.route,
    ) {
        composable(route = NewPostScreens.NewPostGallery.route) {
            AddScreen(appState = appState)
        }
        composable(route = NewPostScreens.NewPost.route) {
            NewPostScreen(appState =appState)
        }
    }
}

sealed class NewPostScreens(val route: String) {
    object NewPostGallery : NewPostScreens(route = "new_post_gallery")
    object NewPost : NewPostScreens(route = "new_post")
}