package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.TAG
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AddScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton


@Composable
fun AddTopBar(
     a :String="",
    navHostController: NavHostController) {

    val navController by remember { mutableStateOf(navHostController) }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
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
            IconButton(onClick = {
                navController.navigate(AddScreens.NewPost.route)
                Log.d(TAG, "AddTopBar: $a")
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
        }
    )
}