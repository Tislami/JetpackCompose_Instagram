package com.zeroone.instagramclone_jetpackcompose.domain.model

import com.zeroone.instagramclone_jetpackcompose.R


data class Post(
    val title: String= "",
    val description: String= "",
    val photoUrl: Int? = null,
    val owner: User,
    val like: Int = 0,
)

val defaultPost = Post(
    title = "Title1",
    description = "Description",
    photoUrl = R.drawable.default_post_image,
    owner = defaultUser,
    like = 5,
    )
