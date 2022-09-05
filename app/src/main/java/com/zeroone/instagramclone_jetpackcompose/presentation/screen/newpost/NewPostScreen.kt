package com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.NewPostTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.TAG
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppProfileImage
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText
import java.io.File

@Composable
fun NewPostScreen(
    user: User = defaultUser,
    navHostController: NavHostController,
    addViewModel: AddViewModel,
) {

    Scaffold(
        topBar = { NewPostTopBar(navHostController) },
        content = {
            Content(
                modifier = Modifier.padding(it),
                user = user,
                addViewModel = addViewModel
            )
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    user: User,
    addViewModel: AddViewModel
) {
    val viewModel by remember { mutableStateOf(addViewModel) }

    Column(modifier = modifier.padding(vertical = 8.dp)) {

        Head(user, viewModel)

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        NewPostButton({ }, R.string.tag_people)
        NewPostButton({ }, R.string.add_location)
        NewPostButton({ }, R.string.add_music)

        AppText(
            text = stringResource(id = R.string.also_post_to),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )


        NewPostButton2({ }, R.string.facebook)
        NewPostButton2({ }, R.string.twitter)

    }
}

@Composable
private fun NewPostButton(onClick: () -> Unit, title: Int) {
    Button(
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        onClick = { onClick() }) {

        AppText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = title),
            fontSize = 16.sp,
        )
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))

}

@Composable
private fun NewPostButton2(onCheckedChange: (Boolean) -> Unit, title: Int) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        AppText(
            text = stringResource(id = title),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )


        Switch(
            checked = false,
            onCheckedChange = onCheckedChange,

            )
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}


@Composable
private fun Head(
    user: User,
    viewModel: AddViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 8.dp)
    ) {
        AppProfileImage(
            painterResourceId = null,
            size = 50.dp
        )


        AppEditTextField(
            value = viewModel.addState.value.caption,
            onValueChange = { viewModel.onEvent(AddEvent.SetCaption(it)) },
            modifier = Modifier
                .fillMaxWidth().weight(.7f)
                .padding(horizontal = 8.dp),
            labelResourceId = R.string.write_a_caption,
        )

        Surface(
            modifier = Modifier
                .size(50.dp).weight(.3f),
        ) {
            Log.d(TAG, "Head:${viewModel.addState.value.photo} ")
            AsyncImage(
                model = File(viewModel.addState.value.photo),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

    }
}

