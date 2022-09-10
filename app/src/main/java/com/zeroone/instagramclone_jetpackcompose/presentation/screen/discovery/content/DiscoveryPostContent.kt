package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.content

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.feeds.FeedsContent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard
import kotlinx.coroutines.launch

@Composable
fun DiscoveryPostContent(
    posts: List<Post>,
    isCollapsed: MutableState<Boolean>,
    onClickUser: (String) -> Unit,
) {

    val clickedPostCount = remember { mutableStateOf(0) }

    if (isCollapsed.value)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(125.dp),
            contentPadding = PaddingValues(4.dp),
        ) {

            items(posts.size, key = { count -> posts[count].id }) { count ->
                CollapsedPostCard(
                    photoUrl = posts[count].photoUrl,
                    onClick = {
                        clickedPostCount.value = count
                        isCollapsed.value = !isCollapsed.value
                    }
                )
            }
        }
    else { FeedsContent(clickedPostCount, posts, onClickUser) }
}