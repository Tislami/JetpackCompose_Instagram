package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.add.AddViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.DiscoveryScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.home.HomeScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val addViewModel: AddViewModel = hiltViewModel()
    val editProfileViewModel: EditProfileViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()

    AnimatedNavHost(
        navController = navController,
        startDestination = Graph.HOME,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        authNavGraph(navController)
        composable(route=Graph.HOME){ HomeScreen(navController) }
        profileNavGraph( navController,editProfileViewModel,userViewModel)
        addNavGraph( navController, addViewModel )
        composable(route = Graph.DISCOVERY) { DiscoveryScreen(navController) }
    }
}

object Graph {
    const val AUTHENTICATION = "AUTH"
    const val HOME = "HOME"
    const val PROFILE = "PROFILE"
    const val DISCOVERY = "DISCOVERY"
    const val ADD = "ADD"
}