package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.OtherUserScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile.ProfileScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileViewModel


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(
    navHostController: NavHostController,
    editProfileViewModel: EditProfileViewModel,
) {

    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.Profile.route,
    ) {
        composable(route = ProfileScreens.Profile.route) {
            ProfileScreen(navHostController= navHostController)
        }
        composable(route = ProfileScreens.EditProfile.route) {
            EditProfileScreen(
                navHostController,
                editProfileViewModel,
            )
        }
        composable(route = ProfileScreens.Followers.route) {
        }
        composable(route = ProfileScreens.Following.route) {
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object Profile : ProfileScreens(route = "profile")
    object EditProfile : ProfileScreens(route = "edit_profile")
    object Followers : ProfileScreens(route = "followers")
    object Following : ProfileScreens(route = "following")
}