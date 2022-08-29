package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph

@Composable
fun AppTopBar(navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    when(currentDestination?.parent?.route){
            Graph.AUTHENTICATION -> { AuthTopBar(currentDestination)}
            Graph.HOME -> {}
            else -> {}
        }
}