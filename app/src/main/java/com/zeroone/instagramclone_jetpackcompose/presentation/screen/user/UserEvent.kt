package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import com.zeroone.instagramclone_jetpackcompose.domain.model.User

sealed class UserEvent {

    data class SetUser(val user: User) : UserEvent()
    data class GetUser(val data: String) : UserEvent()
    data class GetUserPosts(val data: String) : UserEvent()
}