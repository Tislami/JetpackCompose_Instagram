package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.DiscoveryUserCard

@Composable
fun DiscoveryUserContent(
    users: List<User>,
    onClick: () -> Unit,
) {
    LazyColumn{
        items(items = users, key = { user -> user.id }) { user ->
            DiscoveryUserCard(user = user, onClick = onClick)
        }
    }
}