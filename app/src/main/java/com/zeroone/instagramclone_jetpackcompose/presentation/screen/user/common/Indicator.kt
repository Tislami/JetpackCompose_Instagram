package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun Indicator(title: String, count: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = count.toString(),
            fontWeight = FontWeight.Bold
        )
        AppText(
            text = title,
        )
    }
}