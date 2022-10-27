package com.dtu.kd3.kind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.dtu.kd3.kind.controller.Navigation
import com.dtu.kd3.kind.ui.theme.KindTheme
import com.dtu.kd3.kind.views.container.ShowPortFolioView


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KindTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }

}