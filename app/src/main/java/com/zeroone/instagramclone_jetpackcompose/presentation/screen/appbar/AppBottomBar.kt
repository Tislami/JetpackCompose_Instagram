package com.zeroone.instagramclone_jetpackcompose.presentation.screen.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.instagramclone_jetpackcompose.presentation.screen.navigation.AuthScreen

@Composable
fun AppBottomBar(
    items : List<BottomBarScreen>,
    navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (items.any {  it.route == currentDestination?.route})
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 1.dp,
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
}