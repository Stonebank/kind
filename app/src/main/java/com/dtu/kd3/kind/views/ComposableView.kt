package com.dtu.kd3.kind.views

import com.dtu.kd3.kind.R

/**
 * @author s205409 - Hassan Kassem
 *
 * Simple sealed class to contain the route string for the navigation controller
 *
 */

sealed class ComposableView(val title: String, val icon: Int, val route: String) {

    /* LoginView.kt */
    object LoginView : ComposableView("", -1, "login_view")

    /* RegisterView.kt */
    object RegisterView : ComposableView("", -1, "register_view")

    /* HomeView.kt */
    object HomeView: ComposableView("Home", R.drawable.ic_baseline_home_24, "home_view")

    /* PortfolioView.kt */
    object PortfolioView: ComposableView("Portfølje",  R.drawable.ic_baseline_person_24, "portfolio_view")

    /* BuildPortfolioView.kt */
    object BuildPortfolioView: ComposableView("", -1,"build_portfolio_view")

    /* Editprofile.kt */
    object EditProfileView: ComposableView("", -1,"edit_profile_view")

    fun passArguments(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }

}
