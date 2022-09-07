package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.otheruser

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    val useCase: UseCase
) : ViewModel() {

    private val _userState = mutableStateOf(UserState())
    val userState = _userState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isLoading = mutableStateOf(false)
    private var job : Job?= null

    fun getUser(id: String) {
        Log.d("AppAuth", "other_user_viewModel_get: init")
        job?.cancel()
        job = viewModelScope.launch {
            useCase.userUseCase.getUser(id = id).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("AppAuth", "other_user_viewModel_get: error ${response.message}")
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        if (response.data != null) {
                            Log.d("AppAuth", "other_user_viewModel_get: success result ${response.data.posts.size}")
                            _userState.value = userState.value.copy(user = response.data)
                            getUserPosts(id)
                        } else {
                            _eventFlow.emit(UIEvent.Error("Unknown error"))
                            Log.d("AppAuth", "other_user_viewModel_get: success result not completed")
                        }
                    }
                }
            }
        }
    }

    private fun getUserPosts(id : String) {
        Log.d("PostApp", "other_user_viewModel_getUserPosts: init")

        job?.cancel()
        job = viewModelScope.launch {
            useCase.postUseCase.getUserPosts(id).collect { response ->
                when(response){
                    is Response.Error -> {
                        Log.d("PostApp", "other_user_viewModel_getUserPosts: response error ${response.message}")
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> { isLoading.value=true }
                    is Response.Success -> {
                        Log.d("PostApp", "other_user_viewModel_getUserPosts: response success ${response.data.size}")
                        isLoading.value=false
                        _userState.value = userState.value.copy(
                            posts = response.data
                        )
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class Error(val message: String) : UIEvent()
    }

}