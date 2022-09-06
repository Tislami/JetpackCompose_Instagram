package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

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
class DiscoveryViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _postsState = mutableStateOf<Response<List<Post>>>(Response.Loading)
    val postsState = _postsState

    private val _queryState = mutableStateOf<Response<List<User>>>(Response.Success(emptyList()))
    val queryState = _queryState
    val queryText = mutableStateOf("")
    val focusState = mutableStateOf(false)

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        Log.d("discoveryApp", "discoveryViewModel_getAllPosts: init")
        viewModelScope.launch {
            useCase.postUseCase.getPosts().collect {
                _postsState.value = it
                Log.d("discoveryApp", "discoveryViewModel_getAllPosts: result get")

            }
        }
    }

    private var searchJob: Job? = null
    fun query(query: String) {
        queryText.value = query
        Log.d("discoveryApp", "discoveryViewModel_query: init")
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            useCase.mainUseCase.searchUser(query).collect {
                _queryState.value = it
                Log.d("discoveryApp", "discoveryViewModel_query: result get")
            }
        }
    }



    fun setFocus(value: Boolean) {
        focusState.value = value
    }
}