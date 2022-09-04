package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.EditProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit.EditProfileViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton
import kotlinx.coroutines.delay

@Composable
fun CreateUserContent(
    appState: AppState,
    editProfileViewModel: EditProfileViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val navController by remember { mutableStateOf(appState.navHostController) }
    val viewModel by remember { mutableStateOf(editProfileViewModel) }
    val uViewModel by remember { mutableStateOf(userViewModel) }
    val user = viewModel.editProfileState.value


    LaunchedEffect(key1 = "User") {
        uViewModel.eventFlow.collect{ uiEvent ->
            when(uiEvent){
                is UserViewModel.UIEvent.Logged -> {
                    Log.d("AppAuth", "CreateUserContent: User logged event")
                    appState.showToast("Completed")
                    appState.showSnackBar("Completed")
                    delay(30000)
                    appState.navHostController.navigate(Graph.HOME)
                }
                is UserViewModel.UIEvent.Error -> {
                    Log.d("AppAuth", "CreateUserContent: User error event")
                    delay(30000)
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
                        UserEvent.SetUser(
                            name = user.name,
                            lastname = user.lastname,
                            displayName = user.displayName,
                            bio = user.bio,
                            photoUrl = user.photoUrl
                        )
                    )
                },
            )
        },
        content = { Content(
            user= viewModel.editProfileState.value,
            nameValueChange = { viewModel.onEvent(EditProfileEvent.SetName(it)) },
            lastnameValueChange = { viewModel.onEvent(EditProfileEvent.SetLastname(it)) },
            displayNameValueChange = { viewModel.onEvent(EditProfileEvent.SetDisplayName(it)) },
            bioValueChange = { viewModel.onEvent(EditProfileEvent.SetBio(it)) },
            setPhoto = { viewModel.onEvent(EditProfileEvent.SetPhoto(it)) },

        ) })

    Loading(isLoading = viewModel.isLoading.value || uViewModel.isLoading.value)

}

@Composable
private fun Content(
    user: EditProfileState,
    nameValueChange:(String)->Unit,
    lastnameValueChange:(String)->Unit,
    displayNameValueChange:(String)->Unit,
    bioValueChange:(String)->Unit,
    setPhoto:(String)->Unit,
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

        Field(title = stringResource(id = R.string.name),
            value = user.name,
            onValueChange = nameValueChange
        )

        Field(title = stringResource(id = R.string.lastname),
            value = user.lastname,
            onValueChange = lastnameValueChange
        )

        Field(title = stringResource(id = R.string.displayName),
            value = user.displayName,
            onValueChange = displayNameValueChange
        )

        Field(title = stringResource(id = R.string.bio),
            value = user.bio,
            onValueChange = bioValueChange
        )
    }
}

@Composable
fun Field(
    title: String,
    value: String,
    changeable: Boolean = true,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(modifier = Modifier, text = title)
        if (changeable)
            AppEditTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            )
        else
            AppText(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 16.dp), text = value
            )


    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))

}