package com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun DiscoveryUserCard(user: User, onClick: () -> Unit) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppProfileImage(painterResourceId = user.photoUrl!!, size = 75.dp)
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