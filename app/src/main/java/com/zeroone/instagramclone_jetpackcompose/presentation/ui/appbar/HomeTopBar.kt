package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R

@Composable
fun HomeTopBar() {
    TopAppBar(
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.ic_outlined_camera),
                    contentDescription = null
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.app_text_dark),
                    contentDescription = null
                )
            }
        },
        actions = {

            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dm),
                    contentDescription = null
                )
            }
        }
    )
}