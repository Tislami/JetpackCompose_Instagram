package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.AuthBottomBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.AuthTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content.LoginContent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content.RegistrationContent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading

sealed class AuthContents {
    object Login : AuthContents()
    object Registration : AuthContents()
}

@Composable
fun AuthScreen(
    appState: AppState,
    authViewModel: AuthViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val viewModel by remember { mutableStateOf(authViewModel) }
    var content: AuthContents by remember { mutableStateOf(AuthContents.Login) }


    LaunchedEffect(key1 = "Auth") {
        viewModel.eventFlow.collect { uiEvent ->
            when (uiEvent) {
                is AuthViewModel.UIEvent.Login -> {
                    Log.d("AppAuth", "Auth_Screen: Auth login event")
                    userViewModel.onEvent(UserEvent.GetUser(uiEvent.id))
                }
                is AuthViewModel.UIEvent.CreateUser -> {
                    Log.d("AppAuth", "Auth_Screen: Auth create user event")
                    appState.navHostController.navigate(AuthScreens.SetProfile.route)
                }
                is AuthViewModel.UIEvent.Error -> {
                    Log.d("AppAuth", "Auth_Screen: Auth error event")
                    appState.showSnackBar(uiEvent.message)
                }
            }
        }
    }

    LaunchedEffect(key1 = "User") {
        userViewModel.eventFlow.collect { uiEvent ->
            when (uiEvent) {
                is UserViewModel.UIEvent.Set -> {
                    Log.d("AppAuth", "Auth_Screen: User logged event")
                    appState.navHostController.navigate(Graph.HOME)
                }
                is UserViewModel.UIEvent.Error -> {
                    Log.d("AppAuth", "Auth_Screen: User error event")
                    appState.showSnackBar(uiEvent.message)
                }
                is UserViewModel.UIEvent.NotCompleted -> {
                    Log.d("AppAuth", "Auth_Screen: User not completed")
                    appState.navHostController.navigate(AuthScreens.SetProfile.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            AuthTopBar(navigationIconOnClick = {
                content = if (content == AuthContents.Login) AuthContents.Registration
                else AuthContents.Login
            })
        },
        content = {
            if (content == AuthContents.Login)
                LoginContent(
                    authState = viewModel.authState,
                    emailTextValueChange = { viewModel.onEvent(AuthEvent.SetEmail(it)) },
                    passwordTextValueChange = { viewModel.onEvent(AuthEvent.SetPassword(it)) },
                    loginOnClick = {
                        Log.d("AppAuth", "Auth_Screen: login clicked ")
                        viewModel.onEvent(AuthEvent.Login)
                    },
                    navigateToRegistration = { content = AuthContents.Registration },
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                )
            if (content == AuthContents.Registration)
                RegistrationContent(
                    authState = viewModel.authState,
                    emailTextValueChange = { viewModel.onEvent(AuthEvent.SetEmail(it)) },
                    passwordTextValueChange = { viewModel.onEvent(AuthEvent.SetPassword(it)) },
                    signUpOnClick = {
                        Log.d("AppAuth", "Auth_Screen: create user clicked ")
                        viewModel.onEvent(AuthEvent.CreateUser)
                    },
                    navigateToLogin = { content = AuthContents.Login },
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                )
        },
        bottomBar = { AuthBottomBar() }
    )

    Loading(isLoading = viewModel.isLoading.value || userViewModel.isLoading.value)
}