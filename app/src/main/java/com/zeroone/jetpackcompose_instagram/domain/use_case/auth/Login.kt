package com.zeroone.jetpackcompose_instagram.domain.use_case.auth

import com.zeroone.jetpackcompose_instagram.domain.repository.AuthRepository


class Login(private val repository: AuthRepository) {
    operator fun invoke(email : String , password: String) = repository.login(email, password)
}