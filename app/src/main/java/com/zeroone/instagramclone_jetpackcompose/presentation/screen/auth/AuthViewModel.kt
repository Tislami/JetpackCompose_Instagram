package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _authState = mutableStateOf(AuthState())
    val authState = _authState

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetEmail -> {
                _authState.value = authState.value.copy(email = event.data)
            }
            is AuthEvent.SetPassword -> {
                _authState.value = authState.value.copy(password = event.data)
            }
            AuthEvent.Login -> {
                _authState.value = authState.value.copy(password = "event.data")
            }
            AuthEvent.FB -> {
                _authState.value = authState.value.copy(email = "jkndjkfa")
            }
        }
    }
}
