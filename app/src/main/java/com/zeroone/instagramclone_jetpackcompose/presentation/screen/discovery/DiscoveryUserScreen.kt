package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.DiscoveryUserCard

@Composable
fun DiscoveryUserScreen() {
    LazyColumn{
        items(25){
            DiscoveryUserCard(user = defaultUser){}
        }
    }
}