package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login.LoginScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration.RegistrationScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.Login.route,
    ) {
        composable(route = AuthScreens.Login.route) {
            LoginScreen(navHostController = navHostController)
        }
        composable(route = AuthScreens.Registration.route) {
            RegistrationScreen(navHostController = navHostController)
        }
    }
}

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens(route = "login")
    object Registration : AuthScreens(route = "registration")
    object Forgot : AuthScreens(route = "forgot")
}
