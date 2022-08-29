package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText


@Composable
fun AuthBottomBar() {
    BottomAppBar {
        AppText(text = "Instagram To Facebook",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
    }
}