package com.dtu.kd3.kind.views.container

import TextInput
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.input.TextInputType
import com.dtu.kd3.kind.model.User
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.primaryColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.ui.theme.titleColor
import com.dtu.kd3.kind.views.ComposableView

/**
 *
 *
 *  Data is not saved, this is only a demo of how it would work
 */

@Composable
fun ShowEditProfileView(navController: NavController) {

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val user by remember { mutableStateOf(User("KD3")) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(primaryColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(user.name, color = titleColor, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Image(painter = painterResource(id = R.drawable.stock_profile), contentDescription = "profile_picture", contentScale = ContentScale.Crop, modifier = Modifier
                .size(90.dp)
                .clip(
                    CircleShape
                ))
            TextInput(TextInputType.Email, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput("Nuværende kodeord", TextInputType.Password, keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                FocusDirection.Next) }))
            TextInput(TextInputType.Password, keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }), focusRequester = passwordFocusRequester)
            Button(onClick = { navController.navigate( ComposableView.PortfolioView.route) }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), modifier = Modifier.fillMaxWidth()) {
                Text("Bekræft", Modifier.padding(vertical = 8.dp), color = Color.White)
            }
            Divider(color = Color.White.copy(alpha = 0.3f), thickness = 2.dp, modifier = Modifier.padding(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { navController.navigate( ComposableView.PortfolioView.route) }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = secondaryColor), modifier = Modifier.fillMaxWidth()) {
                    Text("Cancel")
                }
            }
        }
    }

}
