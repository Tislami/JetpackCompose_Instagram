package com.zeroone.instagramclone_jetpackcompose.presentation.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun EditProfileScreen(user: User = defaultUser) {
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
        Field(stringResource(id = R.string.username), user.lastname)
        Field(stringResource(id = R.string.bio), stringResource(id = R.string.lorem))

        AppText(
            text = stringResource(id = R.string.private_information),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Start
        )

        Field(stringResource(id = R.string.e_mail), user.email,false)
        Field(stringResource(id = R.string.phone), "000000000000",false)
        Field(stringResource(id = R.string.gender), user.gender,false)
    }
}

@Composable
private fun Field(
    title: String,
    value: String,
    changeable: Boolean=true,
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        AppText(modifier = Modifier.weight(.4f), text = title)

        if (changeable)
            AppEditTextField(value = value, onValueChange = {}, modifier = Modifier.weight(.6f))
        else
            AppText(modifier = Modifier.weight(.4f), text = value)

    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))

}