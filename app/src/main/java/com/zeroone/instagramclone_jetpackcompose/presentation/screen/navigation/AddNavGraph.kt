package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost.AddScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost.NewPostScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addNavGraph(appState: AppState) {
    navigation(
        route = Graph.ADD,
        startDestination = AddScreens.Add.route,
    ) {
        composable(route = AddScreens.Add.route) {
            AddScreen(appState = appState)
        }
        composable(route = AddScreens.NewPost.route) {
            NewPostScreen(appState =appState)
        }
    }
}

sealed class AddScreens(val route: String) {
    object Add : AddScreens(route = "add")
    object NewPost : AddScreens(route = "new_post")
}