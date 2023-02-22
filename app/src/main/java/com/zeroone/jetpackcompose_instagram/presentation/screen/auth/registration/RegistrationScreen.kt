package com.zeroone.jetpackcompose_instagram.presentation.screen.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.jetpackcompose_instagram.R
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.AppTextLogo
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.AuthenticationButton
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.HaveAccount
import com.zeroone.jetpackcompose_instagram.presentation.screen.auth.common.Or
import com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents.AppTextField

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AppTextLogo()

        Spacer(modifier = Modifier.height(16.dp))

        Column{
            AppTextField(text = stringResource(id = R.string.e_mail), onValueChange = {})
            AppTextField(text = stringResource(id = R.string.password), onValueChange = {})
        }

        Spacer(modifier = Modifier.height(32.dp))

        AuthenticationButton(
            text = stringResource(id = R.string.sing_up),
            onClick = {  },
            enabled = true,
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Or()

        HaveAccount(
            text = stringResource(id = R.string.do_you_have_an_account),
            buttonText = stringResource(id = R.string.sing_in),
            onClick = {  },
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))
    }
}