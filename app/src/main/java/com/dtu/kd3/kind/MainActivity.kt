package com.dtu.kd3.kind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import com.dtu.kd3.kind.controller.Navigation
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.model.charities.container.Environment
import com.dtu.kd3.kind.model.charities.container.Health
import com.dtu.kd3.kind.model.charities.container.Immigrants
import com.dtu.kd3.kind.model.charities.container.Social
import com.dtu.kd3.kind.ui.theme.KindTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userModelView: UserViewModel by viewModels()
        setContent {
            KindTheme {
                Surface {
                    Navigation(userViewModel = userModelView)
                }
            }
        }
    }


}