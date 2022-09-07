package com.zeroone.instagramclone_jetpackcompose.presentation.screen.feeds

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.DiscoveryPostTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard

@Composable
fun FeedsScreen(
    appState: AppState,
    feedsViewModel: FeedsViewModel = hiltViewModel(),
    currentItem: String
) {

    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(feedsViewModel) }


    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = { DiscoveryPostTopBar { navController.navigate(Graph.DISCOVERY) } },
        content = {
            when (val result = viewModel.postsState.value) {
                is Response.Error -> {
                    Log.d("discoveryApp", "DiscoveryScreen_posts: response error ${result.message}")
                    LaunchedEffect(key1 = true) { appState.showSnackBar(result.message) }
                }
                is Response.Loading -> Loading()
                is Response.Success -> {
                    Log.d(
                        "discoveryApp",
                        "DiscoveryScreen_posts: response success ${result.data.size}"
                    )
                    Content(result.data, currentItem)
                }
            }
        }
    )
}

@Composable
private fun Content(
    posts: List<Post>,
    currentItem: String
) {

    val firstItem = posts.find { it.id == currentItem }
    val items = posts.filterNot { it.id == currentItem }

    LazyColumn {
        item {
            PostCard(post = firstItem!!)
        }

        items(items = items, key = { post -> post.id }) { post ->
            PostCard(post = post)
        }
    }
}