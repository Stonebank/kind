package com.dtu.kd3.kind.model.charities

/**
 * author: s205409 - Hassan Kassem
 */


class ThemeManager {

    val theme = ArrayList<Theme>()

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