package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.setuser.SetUserScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile.ProfileScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(appState: AppState) {

    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.Profile.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(1000)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.End,
                animationSpec = tween(1000)
            )
        },
    ) {
        composable(route = ProfileScreens.Profile.route) {
            ProfileScreen(appState = appState)
        }
        composable(route = ProfileScreens.EditProfile.route) {
            EditProfileScreen(appState = appState)
        }

    }
}

sealed class ProfileScreens(val route: String) {
    object Profile : ProfileScreens(route = "profile")
    object EditProfile : ProfileScreens(route = "edit_profile")
}