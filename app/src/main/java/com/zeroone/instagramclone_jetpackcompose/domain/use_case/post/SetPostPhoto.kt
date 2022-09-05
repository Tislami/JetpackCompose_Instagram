package com.zeroone.instagramclone_jetpackcompose.domain.use_case.post

import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.repository.PostRepository
import java.io.InputStream
import java.util.zip.DeflaterInputStream

class SetPostPhoto(private val postRepository: PostRepository) {
    operator fun invoke(
        inputStream: InputStream,
        owner:String,
    ) = postRepository.setPostPhoto(
        inputStream = inputStream,
        owner=owner
    )
}