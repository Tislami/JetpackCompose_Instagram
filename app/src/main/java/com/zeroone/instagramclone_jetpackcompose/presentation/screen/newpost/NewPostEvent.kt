package com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost

import java.io.InputStream

sealed class NewPostEvent {
    data class SetCaption(val data:String) : NewPostEvent()
    data class SetPhoto(val data:InputStream) : NewPostEvent()
    object Done : NewPostEvent()
}