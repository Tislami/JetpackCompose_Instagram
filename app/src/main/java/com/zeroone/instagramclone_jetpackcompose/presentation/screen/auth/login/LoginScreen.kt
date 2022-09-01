package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.common.*
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreens
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Content(navHostController, authViewModel)
}

@Composable
private fun Content(
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {

    val navController by remember { mutableStateOf(navHostController) }
    val viewModel by remember { mutableStateOf(authViewModel) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = viewModel.authState,
            emailTextValueChange = { viewModel.onEvent(AuthEvent.SetEmail(it)) },
            passwordTextValueChange = { viewModel.onEvent(AuthEvent.SetPassword(it)) }
        )

        ForgotPassword(onClick = { viewModel.onEvent(AuthEvent.FB) })

        Spacer(modifier = Modifier.height(8.dp))

        AppPrimaryButton(
            text = stringResource(id = R.string.log_in),
            onClick = {navController.navigate("home") },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB(onClick = { viewModel.onEvent(AuthEvent.FB) })

        Or()

        HaveAccount(
            onClick = { navController.navigate(AuthScreens.Registration.route) },
            text = stringResource(id = R.string.dont_have_an_account),
            buttonText = stringResource(id = R.string.sing_up),
        )
    }
}

@Composable
private fun ForgotPassword(onClick: () -> Unit = {}) {
    AppTextButton(
        text = stringResource(id = R.string.forgot_password),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),
        textAlign = TextAlign.End
    )
}





