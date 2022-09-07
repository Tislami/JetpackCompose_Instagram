package com.zeroone.instagramclone_jetpackcompose.presentation.screen.feeds

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _postsState = mutableStateOf<Response<List<Post>>>(Response.Loading)
    val postsState = _postsState

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        Log.d("feedsApp", "feedsViewModel_getAllPosts: init")
        viewModelScope.launch {
            useCase.postUseCase.getPosts().collect {
                _postsState.value = it
                Log.d("feedsApp", "feedsViewModel_getAllPosts: result get")

            }
        }
    }
}