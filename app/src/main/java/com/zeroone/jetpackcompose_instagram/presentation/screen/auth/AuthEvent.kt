package com.zeroone.jetpackcompose_instagram.presentation.screen.auth

sealed class AuthEvent{
    data class SetEmail(val data: String) : AuthEvent()
    data class SetPassword(val data: String) : AuthEvent()
    object CreateUser : AuthEvent()
    object Login : AuthEvent()

}