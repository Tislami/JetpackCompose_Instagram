package com.zeroone.jetpackcompose_instagram.domain.use_case.auth

data class AuthUseCase (
    val createUser: CreateUser,
    val getAuthState: GetAuthState,
    val login: Login,
    val singOut: SignOut,
)