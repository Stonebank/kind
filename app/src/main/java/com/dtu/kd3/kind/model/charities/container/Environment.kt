package com.dtu.kd3.kind.model.charities.container

import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.model.charities.Theme

/**
 * @author s205409 - Hassan K
 *
 * Contributors: s205430 - Muaz Ahmed
 */

class Environment : Theme {

    override fun getCategory(): Category {
        return Category.ENVIRONMENT
    }

    override fun getName(): String {
        return "MILJØ"
    }

    override fun getOrganisations(): Array<String> {
        return arrayOf("Julemærkefonden", "Dansk Naturfredningsforening")
    }

    override fun getDescription(): String {
        return "De fysiske omgivelser og betingelser som mennesker, dyr og planter lever under med gensidig påvirkning. Miljøet er altså de omgivelser vi lever i, både naturlige og menneskeskabte, som mennesker, dyr og planter er med til at påvirke. Påvirkningen kan eksempelvis ske ved, at mennesker fælder træer, dræner jorden eller bygger på den, mens dyr og insekter spiser og bestøver planterne, som vokser og dør og giver næring til jorden og levesteder til dyr og insekter. Miljøproblemer kan eksempelvis være luftforurening, iltsvind i hav og fjorde, tab af biodiversitet, kemisk forurening af jorden og forurening af grundvand med pesticider og andre sprøjtegifte."
    }

    override fun getImage(): Int {
        return R.drawable.environment
    }

}