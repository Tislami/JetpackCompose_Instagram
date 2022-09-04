package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText


@Composable
fun AuthBottomBar() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 1.dp,
    ) {
        AppText(text = "Instagram To Facebook",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
    }
}