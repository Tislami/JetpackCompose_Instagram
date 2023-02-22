package com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zeroone.jetpackcompose_instagram.R

@Composable
fun AppTextLogo(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.app_text_dark)
        else painterResource(id = R.drawable.app_text_light),
        contentDescription = "AppTextLogo",
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}
