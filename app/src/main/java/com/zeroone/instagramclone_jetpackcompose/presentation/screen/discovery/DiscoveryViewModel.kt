package com.zeroone.instagramclone_jetpackcompose.presentation.screen.discovery

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoveryViewModel @Inject constructor() : ViewModel() {

    private val _discoveryState = mutableStateOf(DiscoveryState())
    val discoveryState = _discoveryState

    fun query(query: String){
        _discoveryState.value= discoveryState.value.copy(query=query)
    }

    fun setFocus(value: Boolean){
        _discoveryState.value= discoveryState.value.copy(isFocus = value)
    }
}