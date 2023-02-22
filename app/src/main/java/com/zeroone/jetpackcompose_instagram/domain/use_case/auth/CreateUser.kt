package com.zeroone.jetpackcompose_instagram.domain.use_case.auth

import com.zeroone.jetpackcompose_instagram.domain.repository.AuthRepository

class CreateUser(private val repository: AuthRepository) {
    operator fun invoke(email: String , password: String) =
        repository.createUser(email = email, password=password)
}