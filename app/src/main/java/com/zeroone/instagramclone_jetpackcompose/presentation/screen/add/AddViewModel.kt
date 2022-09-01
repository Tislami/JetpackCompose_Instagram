package com.zeroone.instagramclone_jetpackcompose.presentation.screen.add

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

    private val _addState = mutableStateOf(AddState())
    val addState = _addState

    fun onEvent(event: AddEvent){
        when(event){
            AddEvent.OtherFun -> {

            }
            is AddEvent.SetCaption -> {
                _addState.value= addState.value.copy(caption = event.data)
            }
            is AddEvent.SetPhoto -> {
                _addState.value= addState.value.copy(photo = event.data)
            }
        }
    }
}