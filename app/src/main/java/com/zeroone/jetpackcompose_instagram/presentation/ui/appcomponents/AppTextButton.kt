package com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit


@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    textAlign: TextAlign? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }
}