package com.dtu.kd3.kind.views.container

import TextInput
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.input.TextInputType
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor

/**
 * author s205430 - Muaz Ahmed
 *
 *  Contributors: S205409 - Hassan Kassem
 * *
 */

@Composable
fun ShowEditProfileView(navController: NavController, userViewModel: UserViewModel) {

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 70.dp, top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    RoundedProfileImage(imageID = R.drawable.dtu_logo)
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "THIS FEATURE HAS NOT BEEN ADDED YET!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "edit", tint = Color.Black)
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
                    val email = TextInputType.Email
                    TextInput(email, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Next) }))
                    TextInput("Nuværende kodeord", TextInputType.Password, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Next) }))
                    TextInput(TextInputType.Password, keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }), focusRequester = passwordFocusRequester)
                    Button(onClick = {
                        navController.popBackStack()
                                     }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), modifier = Modifier.fillMaxWidth()) {
                        Text("Bekræft", Modifier.padding(vertical = 8.dp), color = Color.White)
                    }
                }
            }
        }
    }
}
