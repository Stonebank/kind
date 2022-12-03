package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * author: s205409 - Hassan Kassem
 */

class Health : Theme {

    override fun getCategory(): Category {
        return Category.HEALTH
    }

    override fun getName(): String {
        return "SUNDHED"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Hjælp de udsatte med psykiske udfordringer", "Sundhed-velgørenheder: 'Læger Uden Grænser', 'Red Barnet")
    }

}