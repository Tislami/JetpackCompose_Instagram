package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton


@Composable
fun AddTopBar(
    cancelOnClick: ()-> Unit,
    forwardOnClick: ()-> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = cancelOnClick) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
        },
        title = {
            AppText(
                text = stringResource(id = R.string.gallery),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        },

        actions = {
            IconButton(onClick = forwardOnClick) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
        }
    )
}