package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.Main,
        startDestination = Graph.HOME,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) },
    ) {
        authNavGraph(
            signInTextOnClick={navController.navigate(AuthScreen.Login.route)},
            signUpTextOnClick={navController.navigate(AuthScreen.Registration.route)},
            navigateToHome = { navController.navigate(route="home") }
        )
        composable(route="home"){
            HomeScreen()
        }
    }
}

object Graph {
    const val Main = "main"
    const val AUTHENTICATION = "auth"
    const val HOME = "home"
    const val DETAILS = "details"
}