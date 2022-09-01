package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.Indicator
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun ProfileScreen(
    user: User = defaultUser,
    navHostController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { ProfileTopBar() },
        content = {
            Content(
                profileViewModel = profileViewModel,
                user = user,
                navHostController = navHostController,
            )
        }
    )
}

@Composable
private fun Content(
    user: User,
    navHostController: NavHostController,
    profileViewModel: ProfileViewModel,
) {
    Column {
        Head(user, navHostController, profileViewModel)
        Body (user){ navHostController.navigate(ProfileScreens.EditProfile.route) }
    }
}

@Composable
private fun Head(
    user: User,
    navHostController: NavHostController,
    profileViewModel: ProfileViewModel,
) {

    val navController by remember { mutableStateOf(navHostController) }
    val viewModel by remember { mutableStateOf(profileViewModel) }

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

        Indicator(stringResource(id = R.string.posts), user.posts.size) {}

        Indicator(stringResource(id = R.string.followers), user.followers.size) {
            navController.navigate(ProfileScreens.Followers.route)
        }
        Indicator(stringResource(id = R.string.following), user.following.size) {
            navController.navigate(ProfileScreens.Following.route)
        }
    }

    AppButton(
        text = stringResource(id = R.string.edit_profile),
        onClick = { navController.navigate(ProfileScreens.EditProfile.route) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    )

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
private fun Body(
    user: User,
    a: () -> Unit
) {

    if (user.posts.isNotEmpty())
        LazyVerticalGrid(columns = GridCells.Adaptive(125.dp)) {
            items(50) {
                CollapsedPostCard(painterResourceId = R.drawable.default_post_image)
            }
        }
    else Column(
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
        AppTextButton(
            text = stringResource(id = R.string.share_your_first_post),
            onClick = a
        )
    }
}
