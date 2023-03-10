package com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zeroone.jetpackcompose_instagram.R

@Composable
fun AppProfileImage(
    painterResourceId: Int?=null,
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
            painter =
            if (painterResourceId!=null){ painterResource(id = painterResourceId)
            }else{ painterResource(id = R.drawable.default_person_image) },
            contentDescription = contentDescription)
    }
}

@Composable
fun AppProfileImage(
    photoUrl: String?=null,
    contentDescription: String? = null,
    size: Dp =100.dp,
) {
    Surface(
        shape = CircleShape,
        border = BorderStroke(1.dp, color = Color.DarkGray),
        color = MaterialTheme.colors.background,
        modifier = Modifier.size(size)
    ) {
        AsyncImage(
            modifier = Modifier.padding(4.dp),
            model = photoUrl,
            contentDescription = contentDescription)
    }
}