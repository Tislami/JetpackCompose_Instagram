package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Followers
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.Indicator
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun ProfileScreen(
    appState: AppState,
    viewModel: UserViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }

    LaunchedEffect(key1 = "User") {
        viewModel.onEvent(
            UserEvent.GetUserPosts(viewModel.userState.value.user.id,)
        )
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = { ProfileTopBar(viewModel.userState.value.user.displayName) },
        content = {
            Content(
                userState = viewModel.userState.value,
                navigateToFollowers = { navController.navigate(ProfileScreens.Followers.route) },
                navigateToFollowing = { navController.navigate(ProfileScreens.Following.route) },
                navigateToEditProfile = { navController.navigate(ProfileScreens.EditProfile.route) },
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
private fun Content(
    userState: UserState,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
    navigateToEditProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Head(
            userState = userState,
            navigateToFollowers = navigateToFollowers,
            navigateToFollowing = navigateToFollowing,
            navigateToEditProfile = navigateToEditProfile
        )
        Body(
            posts = userState.posts,
            navigateToPost = {}
        )
    }
}

@Composable
private fun Head(
    userState: UserState,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
    navigateToEditProfile: () -> Unit,
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
            AppText(text = userState.user.displayName)
        }


        Indicator(
            title = stringResource(id = R.string.posts),
            count = userState.posts.size,
            onClick = { }
        )

        Indicator(
            title = stringResource(id = R.string.followers),
            count = userState.followers.size,
            onClick = navigateToFollowers
        )

        Indicator(
            title = stringResource(id = R.string.following),
            count = userState.following.size,
            onClick = navigateToFollowing
        )

    }

    AppButton(
        text = stringResource(id = R.string.edit_profile),
        onClick = navigateToEditProfile,
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
    posts: List<Post>,
    navigateToPost: () -> Unit
) {

    if (posts.isNotEmpty())
        LazyVerticalGrid(columns = GridCells.Adaptive(125.dp)) {
            items(posts) { post ->
                CollapsedPostCard(post.photoUrl)
            }
        }
    else
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
            AppTextButton(
                text = stringResource(id = R.string.share_your_first_post),
                onClick = navigateToPost
            )
        }
}

