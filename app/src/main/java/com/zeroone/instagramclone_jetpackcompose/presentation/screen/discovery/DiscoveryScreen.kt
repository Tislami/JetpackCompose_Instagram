package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.DiscoveryTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading

@Composable
fun DiscoveryScreen(
    appState: AppState,
    discoveryViewModel: DiscoveryViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(discoveryViewModel) }

    Scaffold(
        topBar = {
            DiscoveryTopBar(
                query = viewModel.queryText.value,
                onValueChange = viewModel::query,
                onFocusChange = viewModel::setFocus ,
            )
        },
        content = {
            if (viewModel.queryText.value.isNotEmpty()) {
                when (val result = discoveryViewModel.queryState.value) {
                    is Response.Error -> {
                        Log.d("discoveryApp", "DiscoveryScreen_query: response error ${result.message}")
                        LaunchedEffect(key1 = true){
                            appState.showSnackBar(result.message) }
                    }
                    is Response.Loading -> { Loading() }
                    is Response.Success -> {
                        if (result.data.isNotEmpty()) {
                            Log.d("discoveryApp", "DiscoveryScreen_query: response success ${result.data.size}")
                            DiscoveryUserContent(users = result.data, onClick = {})
                        }
                    }
                }
            } else {
                when (val result = discoveryViewModel.postsState.value) {
                    is Response.Error -> {
                        Log.d("discoveryApp", "DiscoveryScreen_posts: response error ${result.message}")
                        LaunchedEffect(key1 = true) {
                            appState.showSnackBar(result.message)
                        }
                    }
                    is Response.Loading -> Loading()
                    is Response.Success -> {
                        Log.d("discoveryApp", "DiscoveryScreen_posts: response success ${result.data.size}")
                        DiscoveryPostContent(posts = result.data, onClick = {})
                    }
                }
            }

        },
    )
}

