package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.otheruser

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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.Indicator
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.ProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard
import kotlinx.coroutines.flow.collect

@Composable
fun OtherUserScreen(
    appState: AppState,
    otherUserViewModel: OtherUserViewModel= hiltViewModel(),
    id:String,
) {

    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(otherUserViewModel) }

    LaunchedEffect(key1 = "User") {
        viewModel.getUser(id)
        viewModel.eventFlow.collect{uiEvent->
            when(uiEvent){
                is OtherUserViewModel.UIEvent.Error -> {
                    appState.showSnackBar(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = { ProfileTopBar(
            viewModel.userState.value.user.displayName,
            onClickBack = { navController.navigate(route = Graph.HOME) }
        ) },
        content = {
            Content(
                userState = viewModel.userState.value,
                navigateToFollowers = { navController.navigate(ProfileScreens.Followers.route) },
                navigateToFollowing = { navController.navigate(ProfileScreens.Following.route) },
                followOnClick = {  },
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
    followOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Head(
            userState = userState,
            navigateToFollowers = navigateToFollowers,
            navigateToFollowing = navigateToFollowing,
            followOnClick = followOnClick
        )
        Body(posts = userState.posts)
    }
}

@Composable
private fun Head(
    userState: UserState,
    navigateToFollowers: () -> Unit,
    navigateToFollowing: () -> Unit,
    followOnClick: () -> Unit,
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
            AppText(text = userState.user.displayName, fontSize = 12.sp)
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        AppPrimaryButton(
            text = stringResource(id = R.string.follow),
            onClick = followOnClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        AppButton(
            text = stringResource(id = R.string.message),
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
    }

    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp))
}

@Composable
private fun Body(posts: List<Post>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(125.dp)) {
        items(posts) { post ->
            CollapsedPostCard(post.photoUrl)
        }
    }
}

