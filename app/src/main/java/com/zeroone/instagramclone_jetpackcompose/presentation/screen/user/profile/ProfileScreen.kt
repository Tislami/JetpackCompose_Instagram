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
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Screens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common.UserInfoFields
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun ProfileScreen(
    appState: AppState,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(userViewModel) }
    val userState = viewModel.userState.value

    LaunchedEffect(key1 = "CurrentUser" ){
        viewModel.onEvent(UserEvent.GetUser)
    }


    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            ProfileTopBar(userState.user.displayName,
                onClickBack = { navController.navigate(route = Screens.Home.route) }
            )
        },
        content = {
            Content(
                userState = userState,
                navigateToFollowers = { navController.navigate(Screens.Follow.route) },
                navigateToFollowing = { navController.navigate(Screens.Follow.route) },
                navigateToEditProfile = { navController.navigate(ProfileScreens.EditProfile.route) },
                navigateToNewPost = { navController.navigate(Graph.NEW_POST) },
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
    navigateToNewPost: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 8.dp))
    {
        UserInfoFields(
            user = userState.user,
            navigateToFollowers = navigateToFollowers,
            navigateToFollowing = navigateToFollowing,
        )

        AppButton(
            text = stringResource(id = R.string.edit_profile),
            onClick = navigateToEditProfile,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        )

        Body(
            posts = userState.posts,
            navigateToNewPost = navigateToNewPost
        )
    }
}


@Composable
private fun Body(
    posts: List<Post>,
    navigateToNewPost: () -> Unit
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
                onClick = navigateToNewPost
            )
        }
}

