package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _loginState = mutableStateOf(AuthState())
    val loginState = _loginState


    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetEmail -> {
                _loginState.value = loginState.value.copy(email = event.data)
            }
            is AuthEvent.SetPassword -> {
                _loginState.value = loginState.value.copy(password = event.data)
            }
            is AuthEvent.SetPasswordVisibility -> {
                _loginState.value = loginState.value.copy(isPasswordVisible = event.data)
            }
            AuthEvent.ForgotPassword -> TODO()
            AuthEvent.Login -> TODO()
        }
    }
}