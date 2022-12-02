package com.dtu.kd3.kind.views.container

import TextInput
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.input.TextInputType
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.primaryColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.views.ComposableView


/**
 * @Author s205409 - Hassan Kassem
 *
 * @TODO:
 *
 *  - Determine if user exists
 *
 * @LoginView.kt is the view that shows the login screen when opening the app unsigned or as a new user
 *
 */

@Composable
fun ShowLoginView(navController: NavController, userViewModel: UserViewModel) {

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    userViewModel.setName("KD3")

    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .background(primaryColor)
            .padding(24.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "kd3_logo",
                Modifier
                    .width(236.dp)
                    .height(200.dp))
            TextInput(TextInputType.Email, keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }))
            TextInput(TextInputType.Password, keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }), focusRequester = passwordFocusRequester)
            Button(onClick = { navController.navigate(route = ComposableView.HomeView.route) }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), modifier = Modifier.fillMaxWidth()) {
                Text("Log ind", Modifier.padding(vertical = 8.dp), color = Color.White)
            }
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                Text("Glemt adgangskode?", color = Color.Black, fontSize = 12.sp)
            }
            Divider(color = Color.White.copy(alpha = 0.3f), thickness = 2.dp, modifier = Modifier.padding(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { navController.navigate( ComposableView.RegisterView.route ) }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = secondaryColor), modifier = Modifier.fillMaxWidth()) {
                    Text("Opret en bruger")
                }
            }
        }
    }

}