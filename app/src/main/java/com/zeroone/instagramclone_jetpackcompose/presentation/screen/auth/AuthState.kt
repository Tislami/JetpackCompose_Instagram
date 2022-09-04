package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.SignOut

data class AuthState(
    val authState : String? = "",
    val email: String= "deneme@gamil.com",
    val password: String= "123456789",
)
