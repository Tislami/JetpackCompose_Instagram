package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

sealed class ProfileEvent{
    data class SetName(val data: String) : ProfileEvent()
    data class SetLastname(val data: String) : ProfileEvent()
    data class SetDisplayName(val data: String) : ProfileEvent()
    data class SetBio(val data: String) : ProfileEvent()
    object Done : ProfileEvent()
    object Cancel : ProfileEvent()
}