package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Discovery : BottomBarScreen(
        route = "discovery",
        title = "Discovery",
        icon = Icons.Default.Search
    )

    object Add : BottomBarScreen(
        route = "add",
        title = "Add",
        icon = Icons.Rounded.Add
    )

    object Favorite : BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}