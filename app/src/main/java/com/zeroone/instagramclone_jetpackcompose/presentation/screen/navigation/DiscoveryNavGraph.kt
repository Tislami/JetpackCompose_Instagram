package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.DiscoveryScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.screen.DiscoveryPostScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.discoveryNavGraph(appState:AppState) {

    navigation(
        route = Graph.DISCOVERY,
        startDestination = DiscoveryScreens.Discovery.route,
    ) {

        argument("id") { type = NavType.StringType }
        composable(route = DiscoveryScreens.Discovery.route) {
            DiscoveryScreen(appState = appState)
        }
        composable(route = DiscoveryScreens.DiscoveryPost.route + "/{id}") {
            DiscoveryPostScreen(appState = appState, currentItem  = it.arguments?.getString("id")!!)
        }
    }
}

sealed class DiscoveryScreens(val route: String) {
    object Discovery : DiscoveryScreens(route = "discovery")
    object DiscoveryPost : DiscoveryScreens(route = "discovery_post")
}