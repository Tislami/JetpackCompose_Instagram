package com.zeroone.instagramclone_jetpackcompose.domain.use_case.post

import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.repository.PostRepository

class SetPost(private val postRepository: PostRepository) {
    operator fun invoke(post: Post) = postRepository.setPost(post = post)
}