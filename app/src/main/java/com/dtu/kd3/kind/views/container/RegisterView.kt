package com.dtu.kd3.kind.views.container

import TextInput
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.database.FirebaseManager
import com.dtu.kd3.kind.input.TextInputType
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.views.ComposableView

/**
 * @author s205409 - Hassan Kassem
 *
 *
 *
 */

@Composable
fun ShowRegisterView(navController: NavController, userViewModel: UserViewModel) {

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    userViewModel.setName("Hassan")

    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "kd3_logo",
                Modifier
                    .width(236.dp)
                    .height(200.dp))
            TextInput(TextInputType.Name, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput(TextInputType.Email, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput("Bekræft e-mail", TextInputType.Email, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput(TextInputType.Password, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput("Bekræft kodeord", TextInputType.Password, keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }), focusRequester = passwordFocusRequester)
            Button(onClick = {
                //FirebaseManager.instance.createAccount("test@test.com", "hello123", userViewModel)
                navController.navigate(route = ComposableView.HomeView.route)
                             }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), modifier = Modifier.fillMaxWidth()) {
                Text("Opret bruger", Modifier.padding(vertical = 8.dp), color = Color.Black)
            }
            Divider(color = Color.White.copy(alpha = 0.3f), thickness = 2.dp, modifier = Modifier.padding(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {  }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = secondaryColor), modifier = Modifier.fillMaxWidth()) {
                    Text("Har du allerede en bruger?")
                }
            }
        }
    }
}

