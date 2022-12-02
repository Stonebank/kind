package com.dtu.kd3.kind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import com.dtu.kd3.kind.controller.Navigation
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.model.charities.container.*
import com.dtu.kd3.kind.ui.theme.KindTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userModelView: UserViewModel by viewModels()
        ThemeManager.instance.theme.add(Social())
        ThemeManager.instance.theme.add(Environment())
        ThemeManager.instance.theme.add(Health())
        ThemeManager.instance.theme.add(Immigrants())
        ThemeManager.instance.theme.add(CancerFoundation())
        setContent {
            KindTheme {
                Surface {
                    Navigation(userViewModel = userModelView)
                }
            }
        }
    }

}