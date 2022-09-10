package com.zeroone.instagramclone_jetpackcompose.domain.use_case.user

import com.zeroone.instagramclone_jetpackcompose.domain.repository.UserRepository

class GetFollow(private val repository: UserRepository)
{
    operator fun invoke(idList: List<String>) = repository.getFollow(idList)
}