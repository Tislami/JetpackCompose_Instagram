package com.zeroone.instagramclone_jetpackcompose.presentation.screen.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultPost
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.PostCard

@Composable
fun HomeScreen() {
    PostCard(post = defaultPost)
}