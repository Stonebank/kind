package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * @author s205409 - Hassan K
 *
 * Contributors: s205430 - Muaz Ahmed
 */

class Social : Theme {

    override fun getCategory(): Category {
        return Category.SOCIAL
    }

    override fun getName(): String {
        return "SOCIAL UDSATTE"
    }

    override fun getOrganisations(): Array<String> {
        return arrayOf("Børnefonden", "Dansk Flygtningehjælp")
    }

    override fun getDescription(): String {
        return "Socialt udsatte voksne omfatter mennesker over 18 år med sociale problemer eller med risiko for at få det. Det drejer sig fx om hjemløshed, misbrug, psykiske lidelser, prostitution eller vold i nære relationer, som giver behov for hjælp efter serviceloven."
    //return arrayOf("Hjælp de hjemløse rundt omkring i Danmark", "SocialUdsatte-velgørenheder: 'Røde Kors', 'Social hjælp', 'Red social'")
    }

    override fun getImage(): Int {
        return R.drawable.featured_charity
    }

}