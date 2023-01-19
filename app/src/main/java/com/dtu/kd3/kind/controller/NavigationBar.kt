package com.dtu.kd3.kind.controller

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dtu.kd3.kind.ui.theme.miscColor
import com.dtu.kd3.kind.views.ComposableView

/**
 *
 *
 * To create the bottom navigation bar, the following references has been used:
 * https://medium.com/geekculture/bottom-navigation-in-jetpack-compose-android-9cd232a8b16
 *
 */


/**
 * BottomNavigation is a composable that creates a bottom navigation bar using the Material design guidelines
 *
 * @param navController: NavController is the navigation controller that is used to navigate between the different views
 *
 */

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        ComposableView.HomeView,
        ComposableView.PortfolioView)
    androidx.compose.material.BottomNavigation(backgroundColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = miscColor,
                unselectedContentColor = Color.Black.copy(0.5f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}