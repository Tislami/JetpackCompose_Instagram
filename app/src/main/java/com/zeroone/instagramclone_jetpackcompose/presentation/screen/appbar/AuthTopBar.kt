package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun AuthTopBar(
    navigationIconOnClick: ()->Unit,

) {
    TopAppBar(
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = navigationIconOnClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {}
    )
}