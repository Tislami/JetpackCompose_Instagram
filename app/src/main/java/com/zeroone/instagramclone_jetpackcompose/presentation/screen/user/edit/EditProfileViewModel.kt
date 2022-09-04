package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {

    private val _editProfileState = mutableStateOf(EditProfileState())
    val editProfileState = _editProfileState

    val isLoading = mutableStateOf(false)


    fun onEvent(event: EditProfileEvent) {
        when (event) {
            EditProfileEvent.Cancel -> {}
            EditProfileEvent.Done -> {

            }
            is EditProfileEvent.SetBio -> {
                _editProfileState.value = editProfileState.value.copy(bio = event.data)
            }
            is EditProfileEvent.SetDisplayName -> {
                _editProfileState.value = editProfileState.value.copy(displayName = event.data)
            }
            is EditProfileEvent.SetLastname -> {
                _editProfileState.value = editProfileState.value.copy(lastname = event.data)
            }
            is EditProfileEvent.SetName -> {
                _editProfileState.value = editProfileState.value.copy(name = event.data)
            }
            is EditProfileEvent.SetPhoto -> {
                _editProfileState.value = editProfileState.value.copy(photoUrl = event.data)
            }
        }
    }

}