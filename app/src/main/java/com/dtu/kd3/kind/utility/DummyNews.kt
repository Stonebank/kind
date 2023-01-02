package com.dtu.kd3.kind.utility

import com.dtu.kd3.kind.R

/**
 * Created by Hassan on 02-01-2023
 * S205409
 * Stonebank
 */
enum class DummyNews(
    val title: String,
    val description: String,
    val url: String,
    val image: Int
) {

    NEWS_ONE(
        "The Time Is Now",
        "The International Day of the Girl was established by the United Nations General Assembly in 2012 in response to advocacy from civil society organizations and girls themselves. Its aim is to focus global attention on issues affecting girls, such as their education and rights. Since the first International Day of the Girl, there have been improvements in these areas, but progress has been uneven. There are still many challenges facing girls, including high rates of early marriage, a lack of access to education, and high rates of HIV infection in some parts of the world. This year's theme for the International Day of the Girl is \"Our Time is Now: Our Rights, Our Future,\" which acknowledges that there is still work to be done to ensure that all girls can realize their potential and rights.",
        "https://www.unicef.org/blog/the-time-is-now",
        R.drawable.article_one
    ),

    NEWS_TWO(
        "UNICEF launches appeal to meet unprecedented humanitarian needs",
        "Today, there are more children in need of humanitarian assistance than at any other time since the Second World War. Across the globe, children are facing a historic confluence of crises – from conflict and displacement to infectious disease outbreaks and soaring rates of malnutrition.\n" +
                "\n" +
                "More than 400 million children live in areas under conflict; an estimated 1 billion children – nearly half the world’s children – live in countries at extreme vulnerability to the impacts of climate change; at least 36.5 million children have been displaced from their homes; and 8 million children under age 5 across 15 crisis-hit countries are at risk of death from severe wasting.\n" +
                "\n" +
                "But the situation is far from hopeless. We know how to reach children at greatest risk and in greatest need. Decisive and timely humanitarian action can save children’s lives, while also sowing the seeds of future development.",
        "https://www.unicef.org/emergencies/unicef-launches-appeal-meet-unprecedented-humanitarian-needs",
        R.drawable.article_two
    ),

    NEWS_THREE(
        "More than 11,000 children killed or injured in Yemen",
        "More than 11,000 children have now been killed or maimed as a result of the conflict in Yemen, according to UNICEF – an average of four a day since the escalation of the conflict in 2015. As these are only the UN-verified incidents, the true toll of this conflict is likely to be far higher.",
        "https://www.unicef.org/press-releases/more-11000-children-killed-or-injured-yemen",
        R.drawable.article_three
    )

}