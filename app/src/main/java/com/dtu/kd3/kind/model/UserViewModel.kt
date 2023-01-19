package com.dtu.kd3.kind.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * author s205409 - Hassan Kassem
 *
 * UserViewModel is a view model that is used to store the user data
 *
 */

class UserViewModel : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _donator = mutableStateOf(false)
    val donator: State<Boolean> = _donator

    private val _donated = mutableStateOf(0.0)
    val donated: State<Double> = _donated

    private val _subscribed = mutableListOf<Theme>()
    val subscribed: List<Theme> = _subscribed

    private val _paymentmethod = mutableStateOf("")
    val paymentmethod: State<String> = _paymentmethod
    
    val _percentages = mutableMapOf<Category, Int>()

    fun setName(name: String) {
        _name.value = name
    }

    fun setDonator(donator: Boolean) {
        _donator.value = donator
    }

    fun setDonation(donation: Double) {
        _donated.value = donation
    }

    fun addTheme(theme: Theme) {
        _subscribed.add(theme)
    }

    fun removeTheme(theme: Theme) {
        _subscribed.remove(theme)
    }

    fun setPaymentMethod(paymentMethod: String) {
        _paymentmethod.value = paymentMethod
    }

    fun setPercentage(category: Category, percentage: Int) {
        _percentages[category] = percentage
    }

    fun getPercentage(category: Category): Int {
        return _percentages[category] ?: if (subscribed.size > 1) 0 else 100
    }
}