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

    override fun getOrganisations(): Array<String> {
        return arrayOf("Folkekirkens Nødhjælp", "Dansk Flygtningehjælp")
    }

    override fun getDescription(): String {
        return "Invandrervelgørenheder er velgørenheder, der specifikt fokuserer på at hjælpe og støtte indvandrere og deres familier. De kan have mange forskellige formål, herunder at hjælpe med at integrere indvandrere i det nye samfund, forbedre deres muligheder for uddannelse og beskæftigelse, og levere nødhjælp og støtte til dem, der har brug for det. Invandrervelgørenheder kan også arbejde for at bekæmpe fordomme og forbedre forståelsen for og accepten af indvandring i det samfund, de opererer i."
    }

    override fun getImage(): Int {
        return R.drawable.immigrants
    }

}