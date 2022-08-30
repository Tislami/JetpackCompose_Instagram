package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun ProfileTopBar() {
    TopAppBar(
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface,
        title = {
            AppText(text = "Display Name")
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }

        }
    )
}