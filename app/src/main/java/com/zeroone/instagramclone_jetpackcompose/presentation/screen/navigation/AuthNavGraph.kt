package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login.LoginScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration.RegistrationScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(
    signInTextOnClick: () -> Unit,
    signUpTextOnClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Registration.route,
        ){
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                signUpTextOnClick = signUpTextOnClick,
                navigateToHome = navigateToHome)
        }
        composable(route = AuthScreen.Registration.route) {
            RegistrationScreen(signInTextOnClick= signInTextOnClick,
                navigateToHome = navigateToHome) }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Registration : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}
