package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.common.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextField

@Composable
fun RegistrationScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    Content(
        navHostController=navHostController,
        authViewModel = authViewModel,
    )
}

@Composable
private fun Content(
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {

    val viewModel by remember { mutableStateOf(authViewModel) }
    val navController by remember { mutableStateOf(navHostController) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = viewModel.authState,
            emailTextValueChange = { viewModel.onEvent(AuthEvent.SetEmail(it)) },
            passwordTextValueChange = { viewModel.onEvent(AuthEvent.SetPassword(it)) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppPrimaryButton(
            text = stringResource(id = R.string.sing_up),
            onClick = { navController.navigate(route = "home") },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB(onClick = { viewModel.onEvent(AuthEvent.FB) })

        Or()

        HaveAccount(
            text = stringResource(id = R.string.do_you_have_an_account),
            buttonText = stringResource(id = R.string.sing_in),
            onClick = { navController.navigate(AuthScreens.Login.route) },
        )
    }
}