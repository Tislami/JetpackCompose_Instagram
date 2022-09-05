package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

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
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun ProfileScreen(
    appState: AppState,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val user = remember { mutableStateOf(userViewModel.userState.value.user) }


    LaunchedEffect(key1 = "User" ){
        userViewModel.onEvent(UserEvent.GetUserPosts(user.value.id))
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = { ProfileTopBar(user.value.displayName) },
        content = {
            Content(
                user = user.value,
                posts=userViewModel.userState.value.posts,
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
    user: User,
    posts: List<Post>,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
    navigateToEditProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Head(
            user = user,
            navigateToFollowers = navigateToFollowers,
            navigateToFollowing = navigateToFollowing,
            navigateToEditProfile = navigateToEditProfile
        )
        Body(
            posts = posts,
            navigateToPost = {}
        )
    }
}

@Composable
private fun Head(
    user: User,
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
            items(posts) {post->
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

