package com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth

data class AuthUseCase (
    val createUser: CreateUser,
    val getAuthState: GetAuthState,
    val login: Login,
    val singOut: SignOut,
)