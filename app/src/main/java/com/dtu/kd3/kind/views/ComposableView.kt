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

    /* set donation view */
    object SetDonationView: ComposableView("", -1,"set_donation_view")

    /* confirm donation view */
    object ConfirmDonationView: ComposableView("", -1,"confirm_donation_view")

    /* Read more view */
    object ReadMoreView: ComposableView("", -1,"read_more_view")

    /* edit percentage view */
    object EditPercentageView: ComposableView("", -1,"edit_percentage_view")

    fun passArguments(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }

}
