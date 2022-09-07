package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.DiscoveryScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.feeds.FeedsScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.home.HomeScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.notification.NotificationScreen
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
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        authNavGraph(appState)
        profileNavGraph(appState)
        newPostNavGraph(appState)

        composable(route = Graph.DISCOVERY) { DiscoveryScreen(appState = appState) }
        composable(route = Graph.HOME) { HomeScreen(appState) }
        composable(route = Graph.NOTIFICATION) { NotificationScreen() }

        argument("userId") { type = NavType.StringType }
        argument("postId") { type = NavType.StringType }

        composable(route = Graph.FEEDS + "/{postId}") {
            FeedsScreen(
                appState = appState,
                currentItem = it.arguments?.getString("postId")!!
            )
        }

        composable(route = Graph.OTHER_USER + "/{userId}") {
            OtherUserScreen(
                appState = appState,
                id = it.arguments?.getString("userId")!!
            )
        }
    }
}

object Graph {
    const val AUTHENTICATION = "AUTH"
    const val NOTIFICATION = "NOTIFICATION"
    const val HOME = "HOME"
    const val PROFILE = "PROFILE"
    const val OTHER_USER = "OTHER_USER"
    const val DISCOVERY = "DISCOVERY"
    const val FEEDS = "FEEDS"
    const val NEW_POST = "NEW_POST"
}