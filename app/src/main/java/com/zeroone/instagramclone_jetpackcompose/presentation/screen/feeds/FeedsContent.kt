package com.zeroone.instagramclone_jetpackcompose.presentation.screen.feeds

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard

@Composable
fun FeedsContent(
    clickedPostCount: MutableState<Int>,
    posts: List<Post>,
    onClickUser: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = "Posts") {
        lazyListState.scrollToItem(clickedPostCount.value)
    }

    LazyColumn(state = lazyListState) {
        items(items = posts, key = { post -> post.id }) { post ->
            PostCard(post = post, onClick = { onClickUser(it) })
        }
    }
}