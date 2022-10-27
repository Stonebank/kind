package com.dtu.kd3.kind.views

/**
 * @author s205409 - Hassan Kassem
 *
 * Simple sealed class to contain the route string for the navigation controller
 *
 */

sealed class ComposableView(val route: String) {

    /* LoginView.kt */
    object LoginView : ComposableView("login_view")

    /* RegisterView.kt */
    object RegisterView : ComposableView("register_view")

    /* HomeView.kt */
    object HomeView: ComposableView("home_view")

    /* PortfolioView.kt */
    object PortfolioView: ComposableView("portfolio_view")

    /* BuildPortfolioView.kt */
    object BuildPortfolioView: ComposableView("build_portfolio_view")

    fun passArguments(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }

}
