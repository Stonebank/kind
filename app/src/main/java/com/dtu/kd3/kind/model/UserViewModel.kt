package com.dtu.kd3.kind.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dtu.kd3.kind.model.charities.Theme

/**
 * author s205409 - Hassan Kassem
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

}