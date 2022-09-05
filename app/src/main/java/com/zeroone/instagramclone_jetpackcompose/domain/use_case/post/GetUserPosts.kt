package com.zeroone.instagramclone_jetpackcompose.domain.use_case.post

import com.zeroone.instagramclone_jetpackcompose.domain.repository.PostRepository

class GetUserPosts(private val repository: PostRepository){
    operator fun invoke(id: String) = repository.getUserPosts(id)
}