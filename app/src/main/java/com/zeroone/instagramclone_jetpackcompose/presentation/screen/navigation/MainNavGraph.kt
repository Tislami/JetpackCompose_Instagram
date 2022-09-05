package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.DiscoveryScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.home.HomeScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.notification.NotificationScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    appState: AppState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        authNavGraph(appState)
        profileNavGraph(appState)
        newPostNavGraph(appState)

        composable(route = Graph.HOME) { HomeScreen(appState) }
        composable(route = Graph.DISCOVERY) { DiscoveryScreen(navController) }
        composable(route = Graph.NOTIFICATION) { NotificationScreen() }
    }
}

object Graph {
    const val AUTHENTICATION = "AUTH"
    const val NOTIFICATION = "NOTIFICATION"
    const val HOME = "HOME"
    const val PROFILE = "PROFILE"
    const val DISCOVERY = "DISCOVERY"
    const val ADD = "ADD"
}