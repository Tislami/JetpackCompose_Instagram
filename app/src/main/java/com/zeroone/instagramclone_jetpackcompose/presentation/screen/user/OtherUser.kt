package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun OtherUserScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {

    val viewModel by remember {
        mutableStateOf(userViewModel)
    }

    Scaffold(
        topBar = { ProfileTopBar() },
        content = {
            Content(
                user = defaultUser,
                navHostController = navHostController,
            )
        }
    )
}

@Composable
private fun Content(
    user: User,
    navHostController: NavHostController,
) {
    Column {
        Head(user = user, navHostController = navHostController)
        Body(posts = user.posts)
    }
}

@Composable
private fun Head(
    user: User,
    navHostController: NavHostController,
) {
    val navController by remember { mutableStateOf(navHostController) }

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

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)) {
        AppPrimaryButton(
            text = stringResource(id = R.string.follow),
            onClick = { navController.navigate(ProfileScreens.EditProfile.route) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        AppButton(
            text = stringResource(id = R.string.message),
            onClick = { navController.navigate(ProfileScreens.EditProfile.route) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
private fun Body(posts: List<Post>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(125.dp)) {
        items(posts) { post ->
            CollapsedPostCard(painterResourceId = post.photoUrl!!)
        }
    }
}

