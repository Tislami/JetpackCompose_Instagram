package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _profileState = mutableStateOf(ProfileState())
    val profileState = _profileState

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.Cancel -> {
                _profileState.value = profileState.value.copy(name = "Cancel")
            }
            ProfileEvent.Done -> {
                _profileState.value = profileState.value.copy(lastname = "Done")
            }
            is ProfileEvent.SetBio -> {
                _profileState.value = profileState.value.copy(bio = event.data)
            }
            is ProfileEvent.SetDisplayName -> {
                _profileState.value = profileState.value.copy(displayName = event.data)
            }
            is ProfileEvent.SetLastname -> {
                _profileState.value = profileState.value.copy(lastname = event.data)
            }
            is ProfileEvent.SetName -> {
                _profileState.value = profileState.value.copy(name = event.data)
            }
        }
    }

}