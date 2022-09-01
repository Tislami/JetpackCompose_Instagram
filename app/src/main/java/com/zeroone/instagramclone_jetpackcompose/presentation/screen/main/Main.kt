package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.MainNavGraph

@Composable
fun Main(navHostController: NavHostController) {

    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Discovery,
        BottomBarScreen.Add,
        BottomBarScreen.Favorite,
        BottomBarScreen.Profile,
    )

    Scaffold(
        content = {
            MainNavGraph(
                modifier = Modifier.padding(it),
                navController = navHostController) },
        bottomBar = { AppBottomBar(items,navHostController) }
        )
}