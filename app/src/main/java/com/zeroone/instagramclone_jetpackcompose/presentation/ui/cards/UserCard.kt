package com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*

@Composable
fun DiscoveryUserCard(
    user: User,
    size: Dp = 75.dp,
    onClick: () -> Unit
) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppProfileImage(painterResourceId = null, size = size)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                AppText(text = user.displayName)
                AppText(
                    text = "${user.name} ${user.lastname}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

@Composable
fun FollowersUserCard(user: User, onClick: () -> Unit) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            AppProfileImage(painterResourceId = null, size = 50.dp)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppText(
                        text = user.displayName,
                        fontSize = 12.sp
                    )

                    AppTextButton(
                        text = stringResource(id = R.string.follow),
                        fontSize = 14.sp
                    )
                }

                AppButton(
                    text = stringResource(id = R.string.remove),
                    onClick = { },
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
fun FollowingUserCard(user: User, onClick: () -> Unit) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AppProfileImage(painterResourceId = null, size = 50.dp)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    AppText(text = user.displayName)
                    AppText(
                        text = "${user.name} ${user.lastname}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButton(
                        text = stringResource(id = R.string.following),
                        onClick = { },
                        fontSize = 12.sp,
                    )

                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}