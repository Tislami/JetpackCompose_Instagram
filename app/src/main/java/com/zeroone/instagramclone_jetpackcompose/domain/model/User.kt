package com.zeroone.instagramclone_jetpackcompose.domain.model

data class User(
    val id: String="",
    val email: String ="",
    val name: String ="",
    val lastname: String ="",
    val displayName: String ="",
    val photoUrl : String? = "",
    val posts : List<String> = emptyList(),
    val followers: List<String> = emptyList(),
    val following: List<String> = emptyList(),
)

val defaultUser = User(
    id = "DcJHfYrm2QZwADadEIkRNaGh9lv1",
    email = "name@gamil.com",
    name = "Name",
    lastname = "Lastname",
    displayName = "Display name",
    photoUrl = "https://firebasestorage.googleapis.com/v0/b/instagram-7e499.appspot.com/o/Users%2FDcJHfYrm2QZwADadEIkRNaGh9lv1%2Fprofile_Image?alt=media&token=4e5336da-aa9a-4bbe-a78a-db31b285c8c5"
)
