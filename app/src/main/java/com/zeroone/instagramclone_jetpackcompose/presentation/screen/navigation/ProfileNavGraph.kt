package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile.EditProfileScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile.ProfileScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(navHostController: NavHostController, ) {


    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.Profile.route,
    ) {
        composable(route = ProfileScreens.Profile.route) {
            ProfileScreen(
                user = defaultUser,
                navHostController= navHostController
            )
        }
        composable(route = ProfileScreens.EditProfile.route) {
            EditProfileScreen(navHostController)
        }
        composable(route = ProfileScreens.Followers.route) {
            EditProfileScreen(navHostController)
        }
        composable(route = ProfileScreens.Following.route) {
            EditProfileScreen(navHostController)
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object Profile : ProfileScreens(route = "profile")
    object EditProfile : ProfileScreens(route = "edit_profile")
    object Followers : ProfileScreens(route = "followers")
    object Following : ProfileScreens(route = "following")
}