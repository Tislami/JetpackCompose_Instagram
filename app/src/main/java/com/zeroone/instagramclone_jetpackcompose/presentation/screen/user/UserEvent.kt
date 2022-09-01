package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import com.zeroone.instagramclone_jetpackcompose.domain.model.User

sealed class UserEvent{
    data class SetUser(val data: User) : UserEvent()
}