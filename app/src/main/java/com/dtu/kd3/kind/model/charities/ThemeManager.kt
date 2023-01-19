package com.dtu.kd3.kind.model.charities

import com.dtu.kd3.kind.model.charities.container.Environment
import com.dtu.kd3.kind.model.charities.container.Health
import com.dtu.kd3.kind.model.charities.container.Immigrants
import com.dtu.kd3.kind.model.charities.container.Social

/**
 * author: s205409 - Hassan Kassem
 *
 * ThemeManager is a singleton that is used to manage the themes
 *
 */


class ThemeManager {

    val theme = ArrayList<Theme>()

    init {
        theme.add(Social())
        theme.add(Environment())
        theme.add(Health())
        theme.add(Immigrants())
    }

    fun getTheme(name: String) : Theme {
        for (t in theme) {
            if (t.getName().equals(name, ignoreCase = true)) {
                return t
            }
        }
        return theme[0]
    }

    companion object {
        val instance = ThemeManager()
    }

}