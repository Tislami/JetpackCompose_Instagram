package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val useCase: UseCase, ) : ViewModel() {

    private val _authState = mutableStateOf(AuthState())
    val authState = _authState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isLoading = mutableStateOf(false)
    private var loginJob: Job? = null

    init {
        getAuthState()
    }

    private fun getAuthState() {
        viewModelScope.launch {
            useCase.authUseCase.getAuthState().collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        _authState.value = authState.value.copy(authState = response.data)
                        isLoading.value = false
                    }
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        Log.d("AppAuth", "auth_viewModel_login:  init")

        loginJob?.cancel()
        loginJob = viewModelScope.launch {
            useCase.authUseCase.login(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("AppAuth", "auth_viewModel_login:  error result ${response.message}")
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        if (response.data!=null){
                            Log.d("AppAuth", "auth_viewModel_login:  success result ${response.data}")
                            _eventFlow.emit(UIEvent.Login(response.data))
                        }
                        else{
                            Log.d("AppAuth", "auth_viewModel_login:  success result null ${response.data}")
                            _eventFlow.emit(UIEvent.Error("Unknown Error"))
                        }

                        isLoading.value = false
                    }
                }
            }
        }
    }



    private fun createUser(email: String, password: String) {
        Log.d("AppAuth", "auth_viewModel_createUser: init")
        viewModelScope.launch {
            useCase.authUseCase.createUser(email, password).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("AppAuth", "auth_viewModel_createUser: error result ${response.message}")

                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        if (response.data!=null){
                            Log.d("AppAuth", "auth_viewModel_createUser: success result ${response.data}")
                            _eventFlow.emit(UIEvent.CreateUser(response.data,email))
                        }
                        else{
                            Log.d("AppAuth", "auth_viewModel_createUser: success result null ${response.data}")
                            _eventFlow.emit(UIEvent.Error("Unknown Error"))
                        }

                        isLoading.value = false
                    }
                }
            }
        }
    }



    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetEmail -> {
                _authState.value = authState.value.copy(email = event.data)
            }
            is AuthEvent.SetPassword -> {
                _authState.value = authState.value.copy(password = event.data)
            }
            AuthEvent.Login -> {
                login(authState.value.email, authState.value.password)
            }
            AuthEvent.CreateUser -> {
                createUser(authState.value.email, authState.value.password)
            }
        }
    }


    sealed class UIEvent {
        data class Error(val message: String) : UIEvent()
        data class Login(val id: String)  : UIEvent()
        data class CreateUser(val id: String,val email: String)  : UIEvent()
    }
}
