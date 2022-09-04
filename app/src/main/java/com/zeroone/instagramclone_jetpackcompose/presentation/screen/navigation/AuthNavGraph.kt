package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content.CreateUserContent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(appState: AppState) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.Auth.route,

    ) {

        composable(route = AuthScreens.Auth.route) {
            AuthScreen(appState = appState)
        }
        composable(route = AuthScreens.SetProfile.route) {
            CreateUserContent(appState = appState)
        }
    }
}


sealed class AuthScreens(val route: String) {
    object Auth : AuthScreens(route = "auth")
    object SetProfile : AuthScreens(route = "set_profile"){

    }
}
