package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {

    private val _userState = mutableStateOf(User())
    val userState = _userState


    fun setUser(user: User){
        _userState.value=user
    }

    fun setName(name:String){
        _userState.value = userState.value.copy(name = name)
    }

    fun setLastname(lastname:String){
        _userState.value = userState.value.copy(lastname = lastname)
    }

    fun setDisplayName(displayName:String){
        _userState.value = userState.value.copy(displayName = displayName)
    }

    fun setBio(bio:String) {
        _userState.value = userState.value.copy(bio = bio)
    }

    fun setPhoto(photoUrl:String) {
        _userState.value = userState.value.copy(photoUrl = photoUrl)
    }

}