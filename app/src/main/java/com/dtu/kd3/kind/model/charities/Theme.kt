package com.dtu.kd3.kind.model.charities

/**
 * author: s205409 - Hassan Kassem
 */


interface Theme {

    fun getCategory(): Category

    fun getName(): String
    fun getDescription(): String

    fun getOrganisations(): Array<String>
    fun getImage(): Int
}