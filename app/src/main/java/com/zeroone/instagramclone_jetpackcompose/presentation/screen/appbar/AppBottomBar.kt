package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.instagramclone_jetpackcompose.R
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.Graph
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.ProfileScreens


@Composable
fun AppBottomBar(
    items: List<BottomBarScreen>,
    navHostController: NavHostController
) {
    val navController by remember { mutableStateOf(navHostController) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (currentDestination?.route == Graph.HOME ||
        currentDestination?.route == Graph.NOTIFICATION ||
        currentDestination?.route == Graph.DISCOVERY ||
        currentDestination?.route == ProfileScreens.Profile.route
            ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 1.dp,
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = stringResource(id = R.string.nav_item))
                    },
                    selected = currentDestination.hierarchy.any { it.route == item.route },
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // re selecting the same item
                            launchSingleTop = true
                            // Restore state when re selecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = Graph.HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Discovery : BottomBarScreen(
        route = Graph.DISCOVERY,
        title = "Discovery",
        icon = Icons.Default.Search
    )

    object Add : BottomBarScreen(
        route = Graph.ADD,
        title = "Add",
        icon = Icons.Rounded.Add
    )

    object Notification : BottomBarScreen(
        route = Graph.NOTIFICATION,
        title = "Notification",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomBarScreen(
        route = Graph.PROFILE,
        title = "Profile",
        icon = Icons.Default.Person
    )
}