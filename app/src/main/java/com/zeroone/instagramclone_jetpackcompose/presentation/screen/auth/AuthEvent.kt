package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

sealed class AuthEvent{
    data class SetEmail(val data: String) : AuthEvent()
    data class SetPassword(val data: String) : AuthEvent()
    data class SetPasswordVisibility(val data: Boolean) : AuthEvent()
    object ForgotPassword : AuthEvent()
    object Login : AuthEvent()

}