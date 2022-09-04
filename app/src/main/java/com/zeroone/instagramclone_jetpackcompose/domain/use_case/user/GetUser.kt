package com.zeroone.instagramclone_jetpackcompose.domain.use_case.user

import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepository

class GetUser(private val userRepository: UserRepository) {
    operator fun invoke(id: String) = userRepository.getUser(id = id)
}