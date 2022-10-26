package com.dtu.kd3.kind

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.dtu.kd3.kind.ui.theme.KindTheme
import com.dtu.kd3.kind.views.LoginView
import com.dtu.kd3.kind.views.ShowRegisterView


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            KindTheme {
                Surface {
                    ShowRegisterView()
                }
            }
        }
    }

    @Composable
    fun ShowLoginPage() {
        LoginView()
    }

}