package com.zeroone.instagramclone_jetpackcompose.presentation.screen.main

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.cards.CollapsedPostCard
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.theme.InstagramClone_JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

val TAG = "Instagram_JetpackCompose"


@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberAnimatedNavController()

            InstagramClone_JetpackComposeTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val appState = AppState(
                        navHostController = navHostController,
                        scaffoldState = rememberScaffoldState(),
                        coroutineScope = rememberCoroutineScope(),
                        context = LocalContext.current
                    )

                    Main(
                        appState=appState,
                        navHostController = navHostController)
                    requestPermissions(LocalContext.current, this)
                }
            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // this method is called after permissions has been granted.
        when (requestCode) {
            101 ->
                // in this case we are checking if the permissions are accepted or not.
                if (grantResults.isNotEmpty()) {
                    val storageAccepted = grantResults[0] === PackageManager.PERMISSION_GRANTED
                    if (storageAccepted) {
                        // if the permissions are accepted we are displaying a toast message
                        // and calling a method to get image path.
                        Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show()
                    } else {
                        // if permissions are denied we are closing the app and displaying the toast message.
                        Toast.makeText(
                            this,
                            "Permissions denied, Permissions are required to use the app..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}

private fun checkPermission(ctx: Context): Boolean {
    // in this method we are checking if the permissions are granted or not and returning the result.
    val result = ContextCompat.checkSelfPermission(ctx, READ_EXTERNAL_STORAGE)
    return result == PackageManager.PERMISSION_GRANTED
}

private fun requestPermissions(ctx: Context, activity: Activity) {
    if (checkPermission(ctx)) {
        // if the permissions are already granted we are calling
        // a method to get all images from our external storage.
        Toast.makeText(ctx, "Permissions granted..", Toast.LENGTH_SHORT).show()
    } else {
        // if the permissions are not granted we are
        // requesting permissions on below line
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 101
        )
    }
}



