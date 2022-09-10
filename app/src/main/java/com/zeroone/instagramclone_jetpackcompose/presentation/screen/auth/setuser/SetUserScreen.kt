package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.setuser

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.EditProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Screens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun SetUserScreen(
    appState: AppState,
    editProfileViewModel: EditProfileViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(editProfileViewModel) }
    val uViewModel by remember { mutableStateOf(userViewModel) }
    val user = viewModel.userState.value

    LaunchedEffect(key1 = "User") {
        uViewModel.eventFlow.collect { uiEvent ->
            when (uiEvent) {
                is UserViewModel.UIEvent.Set -> {
                    Log.d("AppAuth", "CreateUserContent: User logged event")
                    appState.navHostController.navigate(Screens.Home.route)
                }
                is UserViewModel.UIEvent.Error -> {
                    Log.d("AppAuth", "CreateUserContent: User error event")
                    appState.showSnackBar(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            EditProfileTopBar(
                onClickCancel = { navController.popBackStack() },
                onClickDone = {
                    Log.d("AppAuth", "CreateUserContent: done clicked")
                    uViewModel.onEvent(
                        UserEvent.SetUser(viewModel.userState.value)
                    )
                },
            )
        },
        content = {
            Content(
                user = viewModel.userState.value,
                nameValueChange = viewModel::setName,
                lastnameValueChange = viewModel::setLastname,
                displayNameValueChange = viewModel::setDisplayName,
                bioValueChange = viewModel::setBio,
                setPhoto = viewModel::setPhoto,
                )
        })

    Loading(isLoading = uViewModel.isLoading.value)

}

@Composable
private fun Content(
    user: User,
    nameValueChange: (String) -> Unit,
    lastnameValueChange: (String) -> Unit,
    displayNameValueChange: (String) -> Unit,
    bioValueChange: (String) -> Unit,
    setPhoto: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppProfileImage(photoUrl = user.photoUrl, size = 120.dp)
        AppTextButton(text = stringResource(id = R.string.change_profile_photo))

        Divider(modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))

        Field(
            title = stringResource(id = R.string.name),
            value = user.name,
            onValueChange = nameValueChange
        )

        Field(
            title = stringResource(id = R.string.lastname),
            value = user.lastname,
            onValueChange = lastnameValueChange
        )

        Field(
            title = stringResource(id = R.string.displayName),
            value = user.displayName,
            onValueChange = displayNameValueChange
        )

        Field(
            title = stringResource(id = R.string.bio),
            value = user.bio,
            onValueChange = bioValueChange
        )
    }
}

@Composable
fun Field(
    title: String,
    value: String,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(modifier = Modifier, text = title)

        AppEditTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .fillMaxWidth()
        )
    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))

}