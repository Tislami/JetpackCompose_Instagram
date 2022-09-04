package com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth

import com.zeroone.instagramclone_jetpackcompose.domain.repository.AuthRepository


class Login(private val repository: AuthRepository) {
    operator fun invoke(email : String , password: String) = repository.login(email, password)
}