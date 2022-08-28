package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun AppTextButton(
    text: String,
    onClick: ()-> Unit = {},
    textAlign: TextAlign? = null,
    modifier : Modifier = Modifier,
    ) {

    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            modifier = modifier,
            textAlign = textAlign,
        )
    }
}