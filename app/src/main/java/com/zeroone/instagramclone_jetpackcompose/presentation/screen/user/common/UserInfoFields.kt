package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun UserInfoFields(
    user: User,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 32.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            AppProfileImage(painterResourceId = null, size = 90.dp)
            Spacer(modifier = Modifier.height(8.dp))
            AppText(text = user.displayName)
        }

        Indicator(
            title = stringResource(id = R.string.posts),
            count = user.posts.size,
            onClick = { }
        )

        Indicator(
            title = stringResource(id = R.string.followers),
            count = user.followers.size,
            onClick = navigateToFollowers
        )

        Indicator(
            title = stringResource(id = R.string.following),
            count = user.following.size,
            onClick = navigateToFollowing
        )

    }
}
