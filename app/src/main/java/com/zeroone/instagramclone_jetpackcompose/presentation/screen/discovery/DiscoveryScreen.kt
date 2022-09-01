package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.DiscoveryTopBar

@Composable
fun DiscoveryScreen(
    navHostController: NavHostController,
    discoveryViewModel: DiscoveryViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(navHostController) }
    val viewModel by remember { mutableStateOf(discoveryViewModel) }

    Scaffold(
        topBar = {
            DiscoveryTopBar(
                query = viewModel.discoveryState.value.query,
                onValueChange = { viewModel.query(it) },
                onFocusChange = { viewModel.setFocus(it.isFocused) },
            )
        },
        content = {
            if (viewModel.discoveryState.value.query.isNotEmpty()) {
                DiscoveryUserContent()
            } else {
                DiscoveryPostContent()
            }
        },
    )
}