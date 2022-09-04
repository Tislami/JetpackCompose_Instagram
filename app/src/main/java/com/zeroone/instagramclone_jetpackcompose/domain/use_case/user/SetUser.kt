package com.zeroone.instagramclone_jetpackcompose.domain.use_case.user

import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepository

class SetUser(private val userRepository: UserRepository) {
    operator fun invoke(user: User) = userRepository.setUser(user = user)
}