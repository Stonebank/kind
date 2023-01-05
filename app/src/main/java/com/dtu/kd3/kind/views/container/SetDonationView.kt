package com.dtu.kd3.kind.views.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor

/**
 * author s205409 - Hassan Kassem
 */

@Composable
fun ShowSetDonationView(navController: NavController, userViewModel: UserViewModel) {
    var input by remember { mutableStateOf(0) }
    var error by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(start = 20.dp, end = 10.dp)) {
                Text(text = "Indtast beløb", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Dette er beløbet som du vil donere hver måned. Du kan ændre det til enhver tid og der er ingen binding. Minimum beløb er 50 kr.", color = Color.Black.copy(0.5f), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                if (userViewModel.donator.value) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Du støtter med ${userViewModel.donated.value} kr. om måneden",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = input.toString(),
                    onValueChange = { input = it.toIntOrNull() ?: 0 },
                    leadingIcon = {
                        Icon(
                            imageVector = if (error) Icons.Default.Warning else Icons.Default.Add,
                            contentDescription = "",
                            tint = if (error) Color.Red else Color.Black
                        )
                    },
                    label = { Text("Beløb", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
                    placeholder = { Text("0 kr", color = Color.White.copy(0.5f), fontWeight = FontWeight.Bold) },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    singleLine = true,
                    isError = error,
                    keyboardActions = KeyboardActions(onDone = { checkInput(input = input) }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            if (error) {
                Text(
                    text = if (input < 50) "Beløbet skal være på minimum 50 kr." else "", modifier = Modifier.padding(start = 20.dp, top = 5.dp),
                    color = colors.error,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (input < 50) {
                    error = true
                    return@Button
                }
                userViewModel.setDonation(input.toDouble())
                userViewModel.setDonator(true)
                navController.popBackStack()
            }, colors = ButtonDefaults.buttonColors(buttonColor), modifier = Modifier.padding(start = 20.dp), elevation = null) {
                Text("Bekræft beløb", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp,)
            }
        }
    }
}

fun checkInput(input: Int) : Boolean {
    return input >= 50
}
