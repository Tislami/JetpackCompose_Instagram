package com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CollapsedPostCard(photoUrl: String?, onClick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .size(125.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant))
            .clickable { onClick() },
        shape = RectangleShape,
        elevation = 0.dp,
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}