package com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents.AppText
import com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents.AppTextButton

@Composable
fun HaveAccount(text: String, buttonText: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = text,
            color = MaterialTheme.colors.onBackground.copy(.5f)
        )

        AppTextButton(text = buttonText, onClick = onClick)
    }
}