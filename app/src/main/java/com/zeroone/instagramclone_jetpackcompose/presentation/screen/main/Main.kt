package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.MainNavGraph

@Composable
fun Main(
    appState: AppState,
    navHostController: NavHostController
) {

    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Discovery,
        BottomBarScreen.Add,
        BottomBarScreen.Notification,
        BottomBarScreen.Profile,
    )

    Scaffold(
        content = {
            MainNavGraph(
                appState = appState,
                modifier = Modifier.padding(it),
                navController = navHostController
            )
        },
        bottomBar = { AppBottomBar(items, navHostController) }
    )
}