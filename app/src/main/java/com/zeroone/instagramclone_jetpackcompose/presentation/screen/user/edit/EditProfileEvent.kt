package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit

sealed class EditProfileEvent{
    data class SetName(val data: String) : EditProfileEvent()
    data class SetLastname(val data: String) : EditProfileEvent()
    data class SetDisplayName(val data: String) : EditProfileEvent()
    data class SetBio(val data: String) : EditProfileEvent()
    data class SetPhoto(val data: Int) : EditProfileEvent()
    object Done : EditProfileEvent()
    object Cancel : EditProfileEvent()
}