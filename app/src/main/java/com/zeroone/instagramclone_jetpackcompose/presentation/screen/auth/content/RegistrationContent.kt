package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content.*
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton

@Composable
fun RegistrationContent(
    authState: MutableState<AuthState>,
    emailTextValueChange: (String) -> Unit,
    passwordTextValueChange: (String) -> Unit,
    signUpOnClick: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = authState,
            emailTextValueChange = emailTextValueChange,
            passwordTextValueChange = passwordTextValueChange
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppPrimaryButton(
            text = stringResource(id = R.string.sing_up),
            onClick = signUpOnClick,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        WithFB(onClick = { })

        Or()

        HaveAccount(
            text = stringResource(id = R.string.do_you_have_an_account),
            buttonText = stringResource(id = R.string.sing_in),
            onClick = navigateToLogin,
        )
    }
}