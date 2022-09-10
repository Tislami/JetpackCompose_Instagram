package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.DiscoveryTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.content.DiscoveryPostContent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Screens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.DiscoveryPostTopBar

@Composable
fun DiscoveryScreen(
    appState: AppState,
    discoveryViewModel: DiscoveryViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(discoveryViewModel) }
    val isCollapsed = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            if (isCollapsed.value)
                DiscoveryTopBar(
                    query = viewModel.queryText.value,
                    onValueChange = viewModel::query,
                    onFocusChange = viewModel::setFocus,
                )
            else
                DiscoveryPostTopBar { navController.navigate(Screens.Discovery.route) }
        },
        content = {
            if (viewModel.queryText.value.isNotEmpty()) {
                when (val result = discoveryViewModel.queryState.value) {
                    is Response.Error -> {
                        Log.d(
                            "discoveryApp",
                            "DiscoveryScreen_query: response error ${result.message}"
                        )
                        LaunchedEffect(key1 = true) {
                            appState.showSnackBar(result.message)
                        }
                    }
                    is Response.Loading -> {
                        Loading()
                    }
                    is Response.Success -> {
                        if (result.data.isNotEmpty()) {
                            Log.d(
                                "discoveryApp",
                                "DiscoveryScreen_query: response success ${result.data.size}"
                            )
                            DiscoveryUserContent(users = result.data, onClick = { userId ->
                                navController.navigate(Screens.OtherUser.route + "/${userId}")
                            })
                        }
                    }
                }
            } else {
                when (val result = discoveryViewModel.postsState.value) {
                    is Response.Error -> {
                        Log.d(
                            "discoveryApp",
                            "DiscoveryScreen_posts: response error ${result.message}"
                        )
                        LaunchedEffect(key1 = true) {
                            appState.showSnackBar(result.message)
                        }
                    }
                    is Response.Loading -> Loading()
                    is Response.Success -> {
                        Log.d(
                            "discoveryApp",
                            "DiscoveryScreen_posts: response success ${result.data.size}"
                        )
                        DiscoveryPostContent(
                            posts = result.data,
                            isCollapsed = isCollapsed,
                            onClickUser = { userId ->
                                navController.navigate(Screens.OtherUser.route + "/${userId}")
                            }
                        )
                    }
                }
            }

        },
    )
}

