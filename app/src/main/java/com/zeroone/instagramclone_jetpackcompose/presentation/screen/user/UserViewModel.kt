package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {

    private val _userState = mutableStateOf(UserState())
    val profileState = _userState

    init {
        _userState.value = profileState.value.copy(
            user = defaultUser
        )
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.SetUser -> {
                _userState.value = profileState.value.copy(
                    user = event.data
                )
            }
        }
    }

}