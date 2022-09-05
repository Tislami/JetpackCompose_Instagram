package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultPost
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    val useCase: UseCase
) : ViewModel() {

    private val _userState = mutableStateOf(UserState())
    val userState = _userState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isLoading = mutableStateOf(false)


    private fun setUser(name: String, lastname: String, displayName: String, bio: String, photoUrl: String?, )
    {
        val user = User(name = name, lastname = lastname, displayName = displayName, bio = bio, photoUrl = photoUrl,)
        Log.d("AppAuth", "user_viewModel_set: set init")
        viewModelScope.launch {
            useCase.userUseCase.setUser(user).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("AppAuth", "user_viewModel_set: error ${response.message}")
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        Log.d("AppAuth", "user_viewModel_set: success result ${response.data.id}")
                        _userState.value = userState.value.copy(user = response.data)
                        _eventFlow.emit(UIEvent.Logged)
                    }
                }
            }
        }
    }

    private fun getUser(id: String) {
        Log.d("AppAuth", "user_viewModel_get: init")
        viewModelScope.launch {
            useCase.userUseCase.getUser(id = id).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("AppAuth", "user_viewModel_get: error ${response.message}")
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        if (response.data != null) {
                            Log.d("AppAuth", "user_viewModel_get: success result ${response.data.id}")
                            _userState.value = userState.value.copy(user = response.data)
                            _eventFlow.emit(UIEvent.Logged)
                        } else {
                            _eventFlow.emit(UIEvent.NotCompleted)
                            Log.d("AppAuth", "user_viewModel_get: success result not completed")
                        }
                    }
                }
            }
        }
    }

    /* private fun singOut() {
         viewModelScope.launch {
             useCase.userUseCase.singOut().collect { response ->
                 when (response) {
                     is Response.Error -> {
                         isLoading.value = false
                         _eventFlow.emit(UIEvent.Error(response.message))
                     }
                     is Response.Loading -> { isLoading.value = true }
                     is Response.Success -> {
                         _eventFlow.emit(UIEvent.SignOut)
                         isLoading.value = false
                     }
                 }
             }
         }
     }*/

    private fun getUserPosts(id : String) {
        Log.d("PostApp", "user_viewModel_getUserPosts: init")

        viewModelScope.launch {
            useCase.postUseCase.getUserPosts(id).collect { response ->
                when(response){
                    is Response.Error -> {
                        Log.d("PostApp", "user_viewModel_getUserPosts: response error ${response.message}")
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value=true }
                    is Response.Success -> {
                        Log.d("PostApp", "user_viewModel_getUserPosts: response success ${response.data[0].title}")
                        isLoading.value=false
                        _userState.value = userState.value.copy(
                            posts = response.data
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.SetUser -> {
                setUser(
                    name = event.name,
                    lastname = event.lastname,
                    displayName = event.displayName,
                    bio = event.bio,
                    photoUrl = event.photoUrl
                )
            }
            is UserEvent.GetUser -> {
                getUser(event.data)
            }
            is UserEvent.GetUserPosts -> {
                getUserPosts(event.data)
            }
        }
    }

    sealed class UIEvent {
        data class Error(val message: String) : UIEvent()
        object NotCompleted : UIEvent()
        object Logged : UIEvent()
        object SignOut : UIEvent()

    }

}