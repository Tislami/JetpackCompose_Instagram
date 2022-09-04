package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthState
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppPrimaryButton

@Composable
fun LoginContent(
    authState: MutableState<AuthState>,
    emailTextValueChange: (String) -> Unit,
    passwordTextValueChange: (String) -> Unit,
    loginOnClick: () -> Unit,
    navigateToRegistration: () -> Unit,
    modifier: Modifier= Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            authState = authState,
            emailTextValueChange = emailTextValueChange,
            passwordTextValueChange = passwordTextValueChange,
        )

        ForgotPassword(onClick = { })

        Spacer(modifier = Modifier.height(8.dp))

        AppPrimaryButton(

            text = stringResource(id = R.string.log_in),
            onClick = loginOnClick,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )


        WithFB(onClick = { })

        Or()

        HaveAccount(
            onClick = navigateToRegistration,
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





