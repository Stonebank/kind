package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * author: s205409 - Hassan Kassem
 */


class Social : Theme {

    override fun getCategory(): Category {
        return Category.SOCIAL
    }

    override fun getName(): String {
        return "SOCIAL UDSATTE"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Hjælp de hjemløse rundt omkring i Danmark", "Eksempel description...")
    }

}