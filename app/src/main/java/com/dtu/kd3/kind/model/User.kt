package com.dtu.kd3.kind.model

class User(var name: String) {

    var donation: Double = 0.0

    fun setDonationAmount(amount: Double) {
        donation = amount
    }


    val isDonator: Boolean
        get() = donation > 0

}