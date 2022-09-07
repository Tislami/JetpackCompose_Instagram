package com.zeroone.instagramclone_jetpackcompose.presentation.screen.newpost

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appbar.NewPostTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.main.AppState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.Loading
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppEditTextField
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents.AppText

@Composable
fun NewPostScreen(
    appState: AppState,
    newPostViewModel: NewPostViewModel= hiltViewModel(),
) {
    val viewModel by remember { mutableStateOf(newPostViewModel) }
    val navController by remember { mutableStateOf(appState.navHostController) }


    LaunchedEffect(key1 = "New_Post"){
        viewModel.eventFlow.collect{uiEvent->
            when(uiEvent){
                is NewPostViewModel.UIEvent.Error -> {
                    appState.showSnackBar(uiEvent.message)
                    Log.d("PostApp", "NewPostScreen: error event ${uiEvent.message}")
                }
                is NewPostViewModel.UIEvent.Posted -> {
                    navController.navigate(Graph.HOME)
                    Log.d("PostApp", "NewPostScreen: Success")
                }
            }
        }
    }


    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            NewPostTopBar(
                cancelOnClick = { navController.popBackStack() },
                doneOnClick = {
                    Log.d("PostApp", "NewPostScreen: done clicked")
                    viewModel.onEvent(NewPostEvent.Done)}
            )
        },
        content = {padding->
            Content(
                modifier = Modifier.padding(padding),
                caption = viewModel.newPostState.value.caption,
                captionValueChange = {viewModel.onEvent(NewPostEvent.SetCaption(it))},
                photoUrl = viewModel.newPostState.value.photoUrl
            )
        }
    )

    Loading(isLoading = newPostViewModel.isLoading.value)
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    caption: String,
    captionValueChange: (String)->Unit,
    photoUrl: String
) {

    Column(modifier = modifier.padding(vertical = 8.dp)) {

        Head(caption, captionValueChange, photoUrl)

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
    caption: String,
    captionValueChange: (String)->Unit,
    photoUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 8.dp)
    ) {

        AppEditTextField(
            value = caption,
            onValueChange = captionValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.7f)
                .padding(horizontal = 8.dp),
            labelResourceId = R.string.write_a_caption,
        )

        Surface(
            modifier = Modifier
                .size(50.dp)
                .weight(.3f),
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

    }
}

