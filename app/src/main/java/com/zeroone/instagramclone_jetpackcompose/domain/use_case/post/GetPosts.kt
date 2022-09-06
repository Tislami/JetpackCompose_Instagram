package com.zeroone.instagramclone_jetpackcompose.domain.use_case.post

import com.zeroone.instagramclone_jetpackcompose.domain.repository.PostRepository

class GetPosts(private val repository: PostRepository){
 operator fun invoke() = repository.getPosts()
}