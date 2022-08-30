package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun EditProfileTopBar() {
    TopAppBar(
        navigationIcon = {
             AppText(
                 modifier = Modifier.clickable {  },
                 text = stringResource(id = R.string.cancel)) }
        ,
        title = {

            AppText(
                text = stringResource(id = R.string.edit_profile),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) },

        actions = {
            AppTextButton(text = stringResource(id = R.string.done)) }
    )
}