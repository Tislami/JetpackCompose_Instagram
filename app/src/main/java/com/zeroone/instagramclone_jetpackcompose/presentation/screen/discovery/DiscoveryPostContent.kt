package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun DiscoveryPostContent() {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(125.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(45) {

            CollapsedPostCard("")
        }
    }
}