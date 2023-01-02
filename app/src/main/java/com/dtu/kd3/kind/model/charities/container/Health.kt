package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * @author s205409 - Hassan K
 *
 * Contributors: s205430 - Muaz Ahmed
 */

class Health : Theme {

    override fun getCategory(): Category {
        return Category.HEALTH
    }

    override fun getName(): String {
        return "SUNDHED"
    }

    override fun getOrganisations(): Array<String> {
        return arrayOf("Læger Uden Grænser", "Red Barnet")
    }

    override fun getDescription():String {
        return "Når man taler om sundhed, kan det altså betale sig at kigge på hele sin livsstil, da alle elementerne her udgør brikker i det store puslespil. Det er dog helt almindeligt, at man opdeler sundhed i den rent fysiske sundhed og den mentale."
    }

    override fun getImage(): Int {
        return R.drawable.featured_charity_2
    }

}