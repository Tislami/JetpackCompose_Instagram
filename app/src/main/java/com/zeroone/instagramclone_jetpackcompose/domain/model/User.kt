package com.zeroone.instagramclone_jetpackcompose.domain.model

import com.zeroone.instagramclone_jetpackcompose.R

data class User(
    val id: String="",
    val email: String ="",
    val name: String ="",
    val gender: String ="Female",
    val lastname: String ="",
    val displayName: String ="",
    val bio: String = "",
    val photoUrl: Int? = null,
    val posts: List<String> = emptyList(),
    val followers: List<String> = emptyList(),
    val following: List<String> = emptyList(),
)

val defaultUser = User(
    id = "DcJHfYrm2QZwADadEIkRNaGh9lv1",
    email = "name@gamil.com",
    name = "Name",
    lastname = "Lastname",
    displayName = "Display name",
    photoUrl = R.drawable.default_person_image
)
