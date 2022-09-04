package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit

data class EditProfileState(
    val name: String="",
    val lastname: String="",
    val displayName: String="",
    val bio: String="",
    val photoUrl: String?= "",
)
