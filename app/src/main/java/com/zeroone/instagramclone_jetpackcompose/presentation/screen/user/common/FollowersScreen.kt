package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppSearchTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.FollowersUserCard
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.DiscoveryUserCard
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.FollowingUserCard


@Composable
fun FollowersScreen() {

    UserListContent()
}

@Composable
fun UserListContent(
    modifier: Modifier = Modifier,
    index: Int = 0
) {
    var selectedIndex by remember { mutableStateOf(index) }
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedIndex
        ) {
            Tab(
                text = { Text(stringResource(id = R.string.followers)) },
                selected = selectedIndex == 0,
                onClick = {
                    selectedIndex = 0
                }
            )

            Tab(
                text = { Text(stringResource(id = R.string.following)) },
                selected = selectedIndex == 1,
                onClick = {
                    selectedIndex = 1
                }
            )
        }

        AppSearchTextField(value = "", onValueChange = {},
            modifier = Modifier.padding(vertical = 16.dp)
        )


        if (selectedIndex==0){
            FollowersList(lazyListState)
        }
        else{
            FollowingList(lazyListState)
        }

    }
}

@Composable
private fun FollowersList(lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        item {
            DiscoveryUserCard(user = defaultUser, size = 50.dp) {}

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }

        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.background,
            ) {
                AppText(
                    text = stringResource(id = R.string.all_followers),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        items(50) {
            FollowersUserCard(user = defaultUser) {}
        }
    }
}

@Composable
private fun FollowingList(lazyListState: LazyListState) {
    LazyColumn(state = lazyListState) {
        items(50) {
            FollowingUserCard(user = defaultUser) {}
        }
    }
}