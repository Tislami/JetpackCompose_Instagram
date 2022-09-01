package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun AppTextButton(
    text: String,
    onClick: ()-> Unit = {},
    textAlign: TextAlign? = null,
    modifier : Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
    ) {

    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = color,
            modifier = modifier,
            textAlign = textAlign,
        )
    }
}