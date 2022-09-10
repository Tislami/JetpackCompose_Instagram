package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.DiscoveryScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.home.HomeScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.notification.NotificationScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.otheruser.OtherUserScreen

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
        modifier = modifier
    ) {
        authNavGraph(appState)
        profileNavGraph(appState)
        newPostNavGraph(appState)

        composable(route = Screens.Discovery.route) { DiscoveryScreen(appState = appState) }
        composable(route = Screens.Home.route) { HomeScreen(appState) }
        composable(route = Screens.Notification.route) { NotificationScreen() }

        argument("userId") { type = NavType.StringType }
        composable(
            route = Screens.OtherUser.route + "/{userId}") {
            OtherUserScreen(appState = appState, userId = it.arguments?.getString("userId")!!)
        }

        composable(route = Screens.Follow.route) {}

    }
}

object Graph {
    const val AUTHENTICATION = "AUTH"
    const val PROFILE = "PROFILE"
    const val NEW_POST = "NEW_POST"
}

sealed class Screens(val route: String) {
    object Discovery : Screens(route = "discovery")
    object Home : Screens(route = "home")
    object Notification : Screens(route = "notification")
    object OtherUser : Screens(route = "other_user")
    object Follow : Screens(route = "follow")
}