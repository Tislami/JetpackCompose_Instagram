package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R

@Composable
fun AppProfileImage(
    painterResourceId: Int,
    contentDescription: String? = null,
    size: Dp =100.dp,
) {
    Surface(
        shape = CircleShape,
        border = BorderStroke(1.dp, color = Color.DarkGray),
        color = MaterialTheme.colors.background,
        modifier = Modifier.size(size)
    ) {
        Image(
            modifier = Modifier.padding(4.dp),
            painter = painterResource(id = painterResourceId) , contentDescription = contentDescription)
    }
}