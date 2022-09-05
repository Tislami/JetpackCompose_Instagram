package com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultPost
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.user.UserUseCase
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val useCase: UseCase,
    private val userViewModel: UserViewModel
) : ViewModel() {

    private val _newPostState = mutableStateOf(NewPostState())
    val newPostState = _newPostState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val isLoading = mutableStateOf(false)

    fun onEvent(event: NewPostEvent) {
        when (event) {
            is NewPostEvent.SetCaption -> {
                _newPostState.value = newPostState.value.copy(caption = event.data)
            }
            is NewPostEvent.SetPhoto -> {
                setPhoto(event.data)
            }
            NewPostEvent.Done -> {
                savePost()
            }
        }
    }


    private fun savePost() {
        val newPost = Post(
            title = newPostState.value.caption,
            photoUrl = newPostState.value.photoUrl,
            owner = userViewModel.userState.value.user.id
        )
        viewModelScope.launch {
            useCase.postUseCase.setPost(newPost).collect { response ->
                when (response) {
                    is Response.Error -> {
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Posted)
                    }
                }
            }
        }
    }

    private fun setPhoto(inputStream: InputStream) {
        Log.d("PostApp", "viewModel_setPhoto: init ")
        viewModelScope.launch {
            useCase.postUseCase.setPostPhoto(
                inputStream = inputStream,
                owner = userViewModel.userState.value.user.id
            ).collect { response ->
                when (response) {
                    is Response.Error -> {
                        Log.d("PostApp", "viewModel_setPhoto: response error ${response.message} ")
                        isLoading.value = false
                        _eventFlow.emit(UIEvent.Error(response.message))
                    }
                    is Response.Loading -> {
                        isLoading.value = true
                    }
                    is Response.Success -> {
                        isLoading.value=false
                        Log.d("PostApp", "viewModel_setPhoto: response success ")
                        _newPostState.value = newPostState.value.copy(photoUrl = response.data)
                        _eventFlow.emit(UIEvent.PhotoAdded)
                    }
                }
            }
        }
    }


    sealed class UIEvent {
        data class Error(val message: String) : UIEvent()
        object PhotoAdded : UIEvent()
        object Posted : UIEvent()

    }
}