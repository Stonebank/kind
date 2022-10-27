package com.dtu.kd3.kind.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dtu.kd3.kind.views.ComposableView
import com.dtu.kd3.kind.views.container.*

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = ComposableView.LoginView.route) {
        composable(route = ComposableView.LoginView.route) {
            ShowLoginView(navController = navigationController)
        }
        composable(route = ComposableView.RegisterView.route) {
            ShowRegisterView(navController = navigationController)
        }
        composable(
            route = ComposableView.HomeView.route + "/{email}", arguments = listOf(navArgument("email") {
                type = NavType.StringType
                nullable = true
            })) {
            entry -> ShowHomeView(email = entry.arguments?.getString("email"), navController = navigationController)
        }
        composable(route = ComposableView.PortfolioView.route) {
            ShowPortFolioView(navController = navigationController)
        }
        composable(route = ComposableView.BuildPortfolioView.route) {
            ShowBuildPortFolioView(navController = navigationController)
        }
    }
}