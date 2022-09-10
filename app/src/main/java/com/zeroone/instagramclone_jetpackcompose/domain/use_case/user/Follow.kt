package com.zeroone.instagramclone_jetpackcompose.domain.use_case.user

import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepository

class Follow(private val repository: UserRepository)
{
    operator fun invoke(id:String) = repository.follow(id)
}