package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph

@Composable
fun AuthTopBar(
    currentDestination: NavDestination?) {
    when (currentDestination?.parent?.route) {
        Graph.AUTHENTICATION -> {
            when(currentDestination.route){
                AuthScreens.Login.route -> { LoginTopBar()}
                else -> {}
            }
        }
        Graph.HOME -> {}
        else -> {}
    }
}

@Composable
fun LoginTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = "Login")}
    )
}

@Composable
fun RegistrationTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text(text = "Registration")}
    )
}