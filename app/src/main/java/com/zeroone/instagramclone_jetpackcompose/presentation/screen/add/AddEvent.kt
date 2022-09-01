package com.zeroone.instagramclone_jetpackcompose.presentation.screen.add

sealed class AddEvent {
    data class SetCaption(val data:String) : AddEvent()
    data class SetPhoto(val data:String) : AddEvent()
    object OtherFun : AddEvent()
}