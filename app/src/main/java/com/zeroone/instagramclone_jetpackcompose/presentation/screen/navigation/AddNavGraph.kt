package com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.add.AddScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.add.AddViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.add.NewPostScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login.LoginScreen
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration.RegistrationScreen


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addNavGraph(
    navHostController: NavHostController,
    addViewModel: AddViewModel,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AddScreens.Add.route,
    ) {
        composable(route = AddScreens.Add.route) {
            AddScreen(
                navHostController = navHostController,
                addViewModel = addViewModel
            )
        }
        composable(route = AddScreens.NewPost.route) {
            NewPostScreen(
                navHostController = navHostController,
                addViewModel = addViewModel
            )
        }
    }
}

sealed class AddScreens(val route: String) {
    object Add : AddScreens(route = "add")
    object NewPost : AddScreens(route = "new_post")
}