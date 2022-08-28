package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.common.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppButton

@Composable
fun RegistrationScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Content(authViewModel = authViewModel)
}


@Composable
private fun Content(authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            emailTextValue = authViewModel.loginState.value.email,
            emailTextValueChange = { authViewModel.onEvent(event = AuthEvent.SetEmail(it)) },
            passwordTextValue = authViewModel.loginState.value.password,
            passwordTextValueChange ={ authViewModel.onEvent(event = AuthEvent.SetPassword(it)) },
        )


        Spacer(modifier = Modifier.height(32.dp))

        AppButton(
            text = stringResource(id = R.string.sing_up),
            onClick = { },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB()

        Or()

        HaveAccount(
            text = stringResource(id = R.string.do_you_have_an_account),
            buttonText = stringResource(id = R.string.sing_in),
            onClick = {}
        )
    }
}