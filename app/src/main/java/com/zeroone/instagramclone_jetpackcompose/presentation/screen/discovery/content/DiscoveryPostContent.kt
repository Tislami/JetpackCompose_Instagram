package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun DiscoveryPostContent(
    posts: List<Post>,
    onClick: (String) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(125.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(items = posts, key = { post -> post.id }) { post ->

            CollapsedPostCard(
                photoUrl = post.photoUrl,
                onClick = { onClick(post.id) }
            )
        }
    }
}