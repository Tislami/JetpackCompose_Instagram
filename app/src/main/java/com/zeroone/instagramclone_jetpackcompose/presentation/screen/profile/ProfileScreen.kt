package com.zeroone.instagramclone_jetpackcompose.presentation.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppButton
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun ProfileScreen(user: User = defaultUser) {
    Column {
        
        Head(user)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            AppText(
                text = stringResource(id = R.string.profile_desc),
                color = MaterialTheme.colors.onBackground.copy(alpha = .5f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            

            AppText(
                text = stringResource(id = R.string.share_your_first_post),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Head(user: User) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 32.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            AppProfileImage(painterResourceId = user.photoUrl!!, size = 90.dp)
            Spacer(modifier = Modifier.height(8.dp))
            AppText(text = user.displayName, fontSize = 12.sp)
        }

        Indicator(stringResource(id = R.string.posts), user.posts.size)
        Indicator(stringResource(id = R.string.followers), user.followers.size)
        Indicator(stringResource(id = R.string.following), user.following.size)
    }

    AppButton(
        text = stringResource(id = R.string.edit_profile),
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    )
    
    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp))
}


@Composable
private fun Indicator(title: String, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AppText(
            text = count.toString(),
            fontWeight = FontWeight.Bold
        )
        AppText(
            text = title,
        )
    }
}