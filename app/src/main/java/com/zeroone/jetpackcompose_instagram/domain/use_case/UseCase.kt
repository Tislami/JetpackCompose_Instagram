package com.zeroone.jetpackcompose_instagram.domain.use_case

import com.zeroone.jetpackcompose_instagram.domain.use_case.auth.AuthUseCase

data class UseCase (
    val authUseCase: AuthUseCase,
    )