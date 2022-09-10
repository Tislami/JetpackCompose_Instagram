package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import com.zeroone.instagramclone_jetpackcompose.domain.model.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens

data class UserState(
    val user: User = User(),
    val posts: List<Post> = emptyList(),
    val followers: List<User> = emptyList(),
    val following: List<User> = emptyList(),
)