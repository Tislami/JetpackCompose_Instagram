package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun AppLogoImage(
    painter: Painter,
    contentDescription: String?=null,
    modifier: Modifier = Modifier,
) {

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.fillMaxWidth().height(50.dp)
    )

}

@Composable
fun AppLogoImage(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {

    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier.fillMaxWidth().height(50.dp)
    )

}