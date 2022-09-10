package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.otheruser

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
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
    val useCase: UseCase,
    private val userViewModel: UserViewModel,
) : ViewModel() {

    private val _userState = mutableStateOf(UserState())
    val userState = _userState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isFollow = mutableStateOf(false)

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
                            chekUser()
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
                        _userState.value = userState.value.copy(posts = response.data)
                    }
                }
            }
        }
    }

    private fun chekUser() {
        if (userState.value.user.followers.contains(userViewModel.userState.value.user.id)) {
            isFollow.value=true
        }
    }

    fun follow(id: String){
        Log.d("FollowApp", "OtherUserViewModel_follow: init")
        viewModelScope.launch {
            useCase.userUseCase.follow(id).collect {response ->
                when(response){
                    is Response.Error -> {
                        Log.d("FollowApp", "OtherUserViewModel_follow: response error ${response.message}")
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message)) }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        Log.d("FollowApp", "OtherUserViewModel_follow: success")
                        isLoading.value=false
                        isFollow.value=true
                        getUser(id)
                        Log.d("FollowApp", "${userState.value.user.followers.size}:______ ")
                    }
                }
            }
        }
    }

    fun unFollow(id: String){
        Log.d("FollowApp", "OtherUserViewModel_unfollow: init")
        viewModelScope.launch {
            useCase.userUseCase.unFollow(id).collect {response ->
                when(response){
                    is Response.Error -> {
                        Log.d("FollowApp", "OtherUserViewModel_unfollow: response error ${response.message}")
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message)) }
                    is Response.Loading -> { isLoading.value=true }
                    is Response.Success -> {
                        Log.d("FollowApp", "OtherUserViewModel_unfollow: response success")
                        isFollow.value=false
                        isLoading.value=false
                        getUser(id)
                    }
                }
            }
        }
    }

    fun getFollowers(idList: List<String>){
        viewModelScope.launch {
            useCase.userUseCase.getFollow(idList).collect{ response->
                when (response) {
                    is Response.Error -> {
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message)) }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        isLoading.value=false
                        _userState.value = userState.value.copy(
                            followers = response.data
                        )
                    }
                }
            }
        }
    }

    fun getFollowing(idList: List<String>){
        viewModelScope.launch {
            useCase.userUseCase.getFollow(idList).collect{ response->
                when (response) {
                    is Response.Error -> {
                        isLoading.value=false
                        _eventFlow.emit(UIEvent.Error(response.message)) }
                    is Response.Loading -> { isLoading.value = true }
                    is Response.Success -> {
                        isLoading.value=false
                        _userState.value = userState.value.copy(
                            following = response.data
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