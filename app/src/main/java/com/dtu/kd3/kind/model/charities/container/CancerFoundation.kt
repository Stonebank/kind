package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * @author s205409 - Hassan K
 *
 * Contributors: s205430 - Muaz Ahmed
 */
class CancerFoundation : Theme {

    override fun getCategory(): Category {
        return Category.KNEKCANCER
    }

    override fun getName(): String {
        return "Knæk cancer"
    }

    override fun getDescription(): Array<String> {
        return arrayOf("Bidrag til research for at knække cancer", "Cancer-velgørenheder: 'Kræftens Bekæmpelse', 'Cancerfonden'")
    }

}