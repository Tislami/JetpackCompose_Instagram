package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.EditProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun EditProfileScreen(
    navHostController: NavHostController,
    user: User = defaultUser,
) {

    Scaffold(
        topBar = { EditProfileTopBar(navHostController = navHostController) },
        content = { Content(user) })
}

@Composable
private fun Content(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppProfileImage(painterResourceId = user.photoUrl!!, size = 120.dp)
        AppTextButton(text = stringResource(id = R.string.change_profile_photo))

        Divider(modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))

        Field(stringResource(id = R.string.name), user.name)
        Field(stringResource(id = R.string.lastname), user.lastname)
        Field(stringResource(id = R.string.username), user.displayName)
        Field(stringResource(id = R.string.bio), stringResource(id = R.string.lorem))

        AppText(
            text = stringResource(id = R.string.private_information),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Start
        )

        Field(stringResource(id = R.string.e_mail), user.email, false)
        Field(stringResource(id = R.string.phone), "000000000000", false)
        Field(stringResource(id = R.string.gender), user.gender, false)
    }
}

@Composable
fun Field(
    title: String,
    value: String,
    changeable: Boolean = true,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(modifier = Modifier, text = title)


        if (changeable)
            AppEditTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            )
        else
            AppText(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 16.dp), text = value)


    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))

}