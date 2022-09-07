package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppSearchTextField

@Composable
fun DiscoveryTopBar(
    query:String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        Row {
            if (query.isNotEmpty())
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
            AppSearchTextField(
                value = query,
                onValueChange = onValueChange,
            )
        }

    }
}