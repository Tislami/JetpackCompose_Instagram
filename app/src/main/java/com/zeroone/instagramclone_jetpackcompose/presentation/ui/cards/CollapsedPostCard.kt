package com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun CollapsedPostCard(painterResourceId: Int, onClick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .size(125.dp)
            .clickable { onClick() },
        shape = RectangleShape,
        elevation = 0.dp,
    ) {
        Image(
            painter = painterResource(id = painterResourceId),
            contentDescription = null
        )
    }
}