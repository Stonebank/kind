package com.dtu.kd3.kind.controller

/**
 * author s205409 - Hassan Kassem
 */

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.views.ComposableView
import com.dtu.kd3.kind.views.container.*

@Composable
fun Navigation(userViewModel: UserViewModel) {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = ComposableView.LoginView.route) {
        composable(route = ComposableView.LoginView.route) {
            ShowLoginView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.RegisterView.route) {
            ShowRegisterView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.HomeView.route) {
            ShowHomeView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.PortfolioView.route) {
            ShowPortFolioView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.BuildPortfolioView.route) {
            ShowBuildPortFolioView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.EditProfileView.route) {
            ShowEditProfileView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.SetDonationView.route) {
            ShowSetDonationView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(route = ComposableView.EditPercentageView.route) {
            ShowEditPercentageView(navController = navigationController, userViewModel = userViewModel)
        }
        composable(
            route = ComposableView.ReadMoreView.route + "/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
                nullable = true
            })) {
                entry ->
            entry.arguments?.getString("name")?.let { ShowReadMoreView(name = it, navController = navigationController) }
        }
    }
}