package com.zeroone.instagramclone_jetpackcompose.domain.model


data class Post(
    val id: String="",
    val title: String="",
    val description: String= "",
    val photoUrl: String? = null,
    val owner: String="",
    val like: Int = 0,
)

val defaultPost = Post(
    title = "Title1",
    description = "Description",
    photoUrl ="default_post_image,",
    owner = "defaultUser",
    like = 5,
    )
