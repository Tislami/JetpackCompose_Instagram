package com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.EditProfileTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserEvent
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.user.UserViewModel
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppTextButton

@Composable
fun EditProfileScreen(
    navHostController: NavHostController,
    editProfileViewModel: EditProfileViewModel,
) {

    val navController by remember { mutableStateOf(navHostController) }

    Scaffold(
        topBar = {
            EditProfileTopBar(
                { navController.popBackStack() },
                { navController.popBackStack() },
            )
        },
        content = { Content(editProfileViewModel) })
}

@Composable
private fun Content(
    editProfileViewModel: EditProfileViewModel
) {

    val viewModel by remember { mutableStateOf(editProfileViewModel) }
    val user = viewModel.editProfileState.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppProfileImage(painterResourceId = user.photo, size = 120.dp)
        AppTextButton(text = stringResource(id = R.string.change_profile_photo))

        Divider(modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))

        Field(title = stringResource(id = R.string.name),
            value = user.name,
            onValueChange = { viewModel.onEvent(EditProfileEvent.SetName(it)) }
        )

        Field(title = stringResource(id = R.string.lastname),
            value = user.lastname,
            onValueChange = { viewModel.onEvent(EditProfileEvent.SetLastname(it)) }
        )

        Field(title = stringResource(id = R.string.displayName),
            value = user.displayName,
            onValueChange = { viewModel.onEvent(EditProfileEvent.SetDisplayName(it)) }
        )

        Field(title = stringResource(id = R.string.bio),
            value = user.bio,
            onValueChange = { viewModel.onEvent(EditProfileEvent.SetBio(it)) }
        )

        AppText(
            text = stringResource(id = R.string.private_information),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Start
        )

        Field(stringResource(id = R.string.e_mail), "email", false)
        Field(stringResource(id = R.string.phone), "000000000000", false)
        Field(stringResource(id = R.string.gender), "user.gender", false)
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
            AppText(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 16.dp), text = value
            )


    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))

}