package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import com.zeroone.instagramclone_jetpackcompose.domain.model.User

sealed class UserEvent {

    data class CheckUserState(val userId: String) : UserEvent()
    data class SetUser(val user: User) : UserEvent()
    object GetUserPosts : UserEvent()
    object GetFollowers : UserEvent()
    object GetFollowing : UserEvent()
    object GetUser : UserEvent()
    object SingOut: UserEvent()
}