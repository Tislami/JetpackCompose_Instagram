package com.zeroone.instagramclone_jetpackcompose.domain.model



data class Post(
    val title: String= "",
    val description: String= "",
    val photoUrl: String?=null,
    val owner : String = "",
    val like : Int = 0,
)

val defaultPost = Post(
    title = "Title1",
    description = "Description",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/instagram-7e499.appspot.com/o/Posts%2Fn6eIjgtrwrW9hnFDgEJhhkEkqns2%2F-6261030758264512643?alt=media&token=ce61856d-62a6-4b68-aba8-6c65f35addac",
    owner = "",
    like = 5,

    )
