package com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.content

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextField
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.auth.AuthState
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppLogoImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun AppTextLogo() {
    AppLogoImage(
        painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.app_text_dark)
        else painterResource(id = R.drawable.app_text_light)
    )
}

@Composable
fun AuthTextField(
    authState: MutableState<AuthState>,
    emailTextValueChange: (String) -> Unit,
    passwordTextValueChange: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    AppTextField(
        text = authState.value.email,
        onValueChange = emailTextValueChange,
        hint = R.string.e_mail,
        trailingOnClick = { emailTextValueChange("") },
        trailingIcon = R.drawable.ic_clear_24,
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    )

    AppTextField(
        text = authState.value.password,
        onValueChange = passwordTextValueChange,
        hint = R.string.password,
        trailingOnClick = { isPasswordVisible = !isPasswordVisible },
        trailingIcon = if (isPasswordVisible) R.drawable.ic_visibility_24
        else R.drawable.ic_visibility_off_24,
        isPasswordVisible = isPasswordVisible,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun HaveAccount(text: String, buttonText: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppText(text = text)
        AppTextButton(text = buttonText, onClick = onClick)
    }
}

@Composable
fun Or() {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 16.dp)
        )
        AppText(text = stringResource(id = R.string.or))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun WithFB(onClick: () -> Unit) {
    AppTextButton(
        text = stringResource(id = R.string.log_in_with_facebook),
        onClick = onClick,
        modifier = Modifier.padding(vertical = 16.dp),
    )
}