package com.zeroone.instagramclone_jetpackcompose.presentation.screen.add

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar.AddTopBar
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard
import java.io.File

@Composable
fun AddScreen(
    navHostController: NavHostController,
    addViewModel: AddViewModel
) {

    val context = LocalContext.current

    Scaffold(
        topBar = { AddTopBar(navHostController = navHostController, a = addViewModel.addState.value.photo)},
        content = { GridView(context = context, addViewModel = addViewModel)},
        )
}

@Composable
fun GridView(
    context: Context,
    addViewModel: AddViewModel,
) {
    val viewModel by remember { mutableStateOf(addViewModel) }
    val imgList by remember { mutableStateOf(getImagePath(context)) }
    val imageFile = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier
                .weight(.6f)
                .fillMaxSize(),
        ) {
            AsyncImage(
                model = File(imageFile.value),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }


        LazyVerticalGrid(
            columns = GridCells.Adaptive(125.dp),
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .weight(.4f)

        ) {
            items(imgList.size) {
                Surface(
                    modifier = Modifier
                        .size(125.dp)
                        .clickable {
                            imageFile.value = imgList[it]
                            viewModel.onEvent(AddEvent.SetPhoto(imageFile.value))
                        }
                        .border(BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant)),
                    shape = RectangleShape,
                    elevation = 0.dp,
                ) {

                    AsyncImage(
                        alignment = Alignment.Center,
                        model = File(imgList[it]),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )

                }
            }
        }
    }
}