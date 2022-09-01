package com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun PostCard(post: Post) {
    Card(
        elevation = 1.dp,
        shape = RectangleShape,
    ) {
        Column{
            Head(post)
            Body(post)
            Bottom()
        }
    }

}

@Composable
private fun Body(post: Post) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(375.dp)
    ) {
        Image(
            painter = painterResource(id = post.photoUrl!!),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun Bottom() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sharp_comment_24),
                contentDescription = null
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.ic_bookmark_24), contentDescription = null)
        }
    }

    Row(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)){
        AppText(
            text = stringResource(id = R.string.lorem),
            maxLines = 3,
            fontSize = 14.sp,
        )
    }
    
}


@Composable
private fun Head(post: Post) {
    Row(
        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AppProfileImage(
            painterResourceId = post.owner.photoUrl!!, size = 56.dp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp)
        ) {

            AppText(
                text = post.owner.displayName,
                fontWeight = FontWeight.Bold,
            )
            AppText(
                text = post.title,
                fontSize = 14.sp
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
    }
}