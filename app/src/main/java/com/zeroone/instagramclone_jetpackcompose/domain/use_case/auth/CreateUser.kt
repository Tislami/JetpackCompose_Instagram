package com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth

import com.zeroone.instagramclone_jetpackcompose.domain.repository.AuthRepository

class CreateUser(private val repository: AuthRepository) {
    operator fun invoke(email: String , password: String) =
        repository.createUser(email = email, password=password)
}