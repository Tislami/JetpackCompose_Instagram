package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

sealed class UserEvent {

    data class SetUser(
        val name: String,
        val lastname: String,
        val displayName: String,
        val bio: String,
        val photoUrl: String?=null,
    ) : UserEvent()

    data class GetUser(val data: String) : UserEvent()
    data class GetUserPosts(val data: String) : UserEvent()
}