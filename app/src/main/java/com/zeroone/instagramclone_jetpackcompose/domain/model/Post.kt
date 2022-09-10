package com.zeroone.instagramclone_jetpackcompose.domain.model


data class Post(
    val id: String="",
    val caption: String="",
    val photoUrl: String? = null,
    val owner: HashMap<String, String?> = hashMapOf(),
    val like: Int = 0,
)

val defaultPost = Post(
    id="",
    caption = "Deneme",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/jet-instagram.appspot.com/o/post_images%2FvzpeXe5mb8WgaN8NesUPmzweXpN2%2F-8032594539584704312?alt=media&token=22f4589c-0be4-4ce4-b437-4f7288e26f86",
    owner = hashMapOf(
        "id" to "hhbhj",
        "photoUrl" to "https://firebasestorage.googleapis.com/v0/b/jet-instagram.appspot.com/o/post_images%2FvzpeXe5mb8WgaN8NesUPmzweXpN2%2F-8032594539584704312?alt=media&token=22f4589c-0be4-4ce4-b437-4f7288e26f86",
        "displayName" to "Deneme"
    )
)