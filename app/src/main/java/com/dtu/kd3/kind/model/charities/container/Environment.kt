package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

class Environment : Theme {

    override fun getCategory(): Category {
        return Category.ENVIRONMENT
    }

    override fun getName(): String {
        return "MILJØ"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Bidrag med at forbedre miljøet", "Miljø-velgørenheder: 'Julemærkefonden', 'Dansk Naturfredningsforening'")
    }
}