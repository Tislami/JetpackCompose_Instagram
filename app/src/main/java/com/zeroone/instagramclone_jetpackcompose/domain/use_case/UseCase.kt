package com.zeroone.instagramclone_jetpackcompose.domain.use_case

import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.AuthUseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.auth.Login
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.post.PostUseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.UserUseCase

data class UseCase (
    val authUseCase: AuthUseCase,
    val userUseCase: UserUseCase,
    val postUseCase: PostUseCase,
)