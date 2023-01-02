package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * @author s205409 - Hassan K
 *
 * Contributors: s205430 - Muaz Ahmed
 */

class Immigrants : Theme {

    override fun getCategory(): Category {
        return Category.IMMIGRANTS
    }

    override fun getName(): String {
        return "UDLÆDNINGE"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Hjælp udlændingerne ved at give dem en god start", "Udlændinge-velgørenheder: 'Folkekirkens Nødhjælp', 'Dansk Flygtningehjælp'")
    }

    override fun getImage(): Int {
        return R.drawable.immigrants
    }

}