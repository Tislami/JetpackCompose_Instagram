package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun EditProfileTopBar(
    onClickCancel:()->Unit,
    onClickDone:()->Unit,
) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onClickCancel ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
        },
        title = {
            AppText(
                text = stringResource(id = R.string.edit_profile),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        },

        actions = {
            IconButton(onClick =  onClickDone) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
        }
    )
}