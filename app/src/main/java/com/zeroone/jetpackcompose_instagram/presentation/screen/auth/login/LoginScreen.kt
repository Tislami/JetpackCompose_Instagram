package com.zeroone.jetpackcompose_instagram.presentation.screen.auth.content

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zeroone.jetpackcompose_instagram.R
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.AppTextLogo
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.AuthenticationButton
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.HaveAccount
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.Or
import com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents.AppTextField
import com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents.AppTextButton

@Composable
fun LoginScreen(
    loginOnClick: () -> Unit,
    navigateToRegistration: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppTextLogo()
        
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            AppTextField(text = stringResource(id = R.string.e_mail), onValueChange = {})
            AppTextField(text = stringResource(id = R.string.password), onValueChange = {})
        }

        ForgotPassword(onClick = { })

        Spacer(modifier = Modifier.height(8.dp))

        AuthenticationButton(
            text = stringResource(id = R.string.log_in),
            onClick = loginOnClick,
            enabled = true,
        )


        LoginWithFacebook(onClick = { })

        Or()

        HaveAccount(
            text = stringResource(id = R.string.dont_have_an_account),
            buttonText = stringResource(id = R.string.sing_up),
            onClick = navigateToRegistration,
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))
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

@Composable
private fun LoginWithFacebook(onClick: () -> Unit) {
    AppTextButton(
        text = stringResource(id = R.string.log_in_with_facebook),
        onClick = onClick,
        modifier = Modifier.padding(vertical = 16.dp),
    )
}







