package com.zeroone.instagramclone_jetpackcompose.domain.model


data class Post(
    val id: String="",
    val caption: String="",
    val photoUrl: String? = null,
    val owner: HashMap<String, String?> = hashMapOf(),
    val like: Int = 0,
)
