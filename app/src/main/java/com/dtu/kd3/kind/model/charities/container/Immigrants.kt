package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

class Immigrants : Theme {

    override fun getCategory(): Category {
        return Category.IMMIGRANTS
    }

    override fun getName(): String {
        return "UDLÆDNINGE"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Hjælp udlændingerne ved at give dem en god start", "Eksempel description...")
    }

}