package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

data class AuthState(
    val email: String= "",
    val password: String= "",
    val isPasswordVisible: Boolean= false,
)
