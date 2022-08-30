package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.common.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton

@Composable
fun RegistrationScreen(
    signInTextOnClick: () -> Unit,
    navigateToHome: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    Content(
        signInTextOnClick = signInTextOnClick,
        navigateToHome = navigateToHome,
        authViewModel = authViewModel,
    )
}

@Composable
private fun Content(
    navigateToHome: () -> Unit,
    signInTextOnClick: () -> Unit,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = authViewModel.authState,
            emailTextValueChange = { authViewModel.onEvent(AuthEvent.SetEmail(it)) },
            passwordTextValueChange = { authViewModel.onEvent(AuthEvent.SetPassword(it)) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppPrimaryButton(
            text = stringResource(id = R.string.sing_up),
            onClick = { navigateToHome() },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB(onClick = { authViewModel.onEvent(AuthEvent.FB) })

        Or()

        HaveAccount(
            text = stringResource(id = R.string.do_you_have_an_account),
            buttonText = stringResource(id = R.string.sing_in),
            onClick = { signInTextOnClick() },
        )
    }
}