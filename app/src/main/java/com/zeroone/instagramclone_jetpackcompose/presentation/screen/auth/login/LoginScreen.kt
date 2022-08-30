package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.common.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton

@Composable
fun LoginScreen(
    signUpTextOnClick: () -> Unit,
    navigateToHome: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Content(
        signUpTextOnClick = signUpTextOnClick,
        navigateToHome = navigateToHome,
        authViewModel = authViewModel
    )

}

@Composable
private fun Content(
    signUpTextOnClick: () -> Unit,
    navigateToHome: () -> Unit,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = authViewModel.authState,
            emailTextValueChange = { authViewModel.onEvent(AuthEvent.SetEmail(it)) },
            passwordTextValueChange = { authViewModel.onEvent(AuthEvent.SetPassword(it)) }
        )

        ForgotPassword(onClick = { authViewModel.onEvent(AuthEvent.FB) })

        Spacer(modifier = Modifier.height(8.dp))

        AppPrimaryButton(
            text = stringResource(id = R.string.log_in),
            onClick = { navigateToHome() },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB(onClick = { authViewModel.onEvent(AuthEvent.FB) })

        Or()

        HaveAccount(
            onClick = { signUpTextOnClick() },
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





