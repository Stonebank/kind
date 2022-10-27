package com.dtu.kd3.kind.utility

/**
 *
 * @author: s205409 - Hassan Kassem
 *
 * CategoryTestData is a simple enum that contains two values. It is to demonstrate how our BuildPortfolioView could look like
 */

enum class CategoryTestData(val title: String, val description: String) {

    SOCIAL("social udsatte", "Hjælp de hjemløse rundt omkring i Danmark"),
    HEALTH("sundhed", "Hjælp udsatte med psykiske problemer"),
    ENVIRONMENT("miljø", "Hjælp med at forbedre miljøet"),
    IMMIGRANTS("Udlændinge", "Hjælp udlændingerne ved at give dem en god start"),
    RED_CROSS("Rød kors", "Hjælp rød kors til at støtte mange forskellige organisationer");

}