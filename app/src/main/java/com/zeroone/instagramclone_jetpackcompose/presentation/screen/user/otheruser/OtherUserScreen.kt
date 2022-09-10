package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.otheruser

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Screens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common.Indicator
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common.UserInfoFields
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard

@Composable
fun OtherUserScreen(
    appState: AppState,
    otherUserViewModel: OtherUserViewModel = hiltViewModel(),
    userId: String
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(otherUserViewModel) }

    val userState = otherUserViewModel.userState.value

    LaunchedEffect(key1 = "User") {
        viewModel.getUser(userId)
        viewModel.eventFlow.collect { uiEvent ->
            when (uiEvent) {
                is OtherUserViewModel.UIEvent.Error -> {
                    appState.showSnackBar(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            ProfileTopBar(
                displayName = userState.user.displayName,
                onClickBack = { navController.navigate(route = Screens.Home.route) }
            )
        },
        content = {
            Content(
                userState = userState,
                navigateToFollowers = { navController.navigate(Screens.Follow.route) },
                navigateToFollowing = { navController.navigate(Screens.Follow.route) },
                followOnClick = {
                    Log.d("FollowApp", "OtherUserScreen: followClicked")
                    viewModel.follow(userId)
                },
                unFollowOnClick = {
                    Log.d("FollowApp", "OtherUserScreen: unFollowClicked")
                    viewModel.unFollow(userId)
                },
                isFollow = viewModel.isFollow.value,
                modifier = Modifier.padding(it)
            )
        }
    )

    Loading(isLoading = viewModel.isLoading.value)
}

@Composable
private fun Content(
    userState: UserState,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
    followOnClick: () -> Unit,
    unFollowOnClick: () -> Unit,
    isFollow: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        UserInfoFields(
            user = userState.user,
            navigateToFollowers = navigateToFollowers,
            navigateToFollowing = navigateToFollowing
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            if (!isFollow)
                AppPrimaryButton(
                    text = stringResource(id = R.string.follow),
                    onClick = followOnClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
            else
                AppButton(
                    text = stringResource(id = R.string.unfollow),
                    onClick = unFollowOnClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )

            AppButton(
                text = stringResource(id = R.string.message),
                onClick = { },
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

        Body(posts = userState.posts)
    }
}

@Composable
private fun Body(posts: List<Post>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(125.dp)) {
        items(posts) { post ->
            CollapsedPostCard(post.photoUrl)
        }
    }
}

