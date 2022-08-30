package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppSearchTextField

@Composable
fun DiscoveryTopBar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent) {
        AppSearchTextField(
            value = "",
            onValueChange = {},
            labelText = stringResource(id = R.string.search)
        )
    }
}