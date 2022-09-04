package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.MainNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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