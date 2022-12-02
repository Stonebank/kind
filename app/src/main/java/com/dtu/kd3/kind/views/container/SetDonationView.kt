package com.dtu.kd3.kind.views.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.primaryColor
import com.dtu.kd3.kind.ui.theme.secondaryButtonColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.views.ComposableView

/**
 * author s205409 - Hassan Kassem
 */

@Composable
fun ShowSetDonationView(navController: NavController, userViewModel: UserViewModel) {
    var input by remember { mutableStateOf(0) }
    var error by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor),
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Indstil dit månedlige donation",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = input.toString(),
                onValueChange = { input = it.toIntOrNull() ?: 0 },
                leadingIcon = {
                    Icon(
                        imageVector = if (error) Icons.Default.Warning else Icons.Default.Edit,
                        contentDescription = "",
                        tint = if (error) Color.Red else Color.White
                    )
                },
                label = { Text("Belæb", color = Color.White) },
                placeholder = { Text("Indtast beløb", color = Color.White) },
                colors = TextFieldDefaults.textFieldColors(Color.White),
                singleLine = true,
                isError = error,
                keyboardActions = KeyboardActions(onDone = { checkInput(input = input) })
            )
        }
        if (error) {
            Text(
                text = if (input < 50) "" else "",
                color = colors.error,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Button(onClick = {
        if (input < 50) {
            error = true
            return@Button
        }
        userViewModel.setDonation(input.toDouble())
        userViewModel.setDonator(true)
        navController.navigate(ComposableView.HomeView.route)
    }, colors = ButtonDefaults.buttonColors(Color.Black)) {
        Text("Doner", color = Color.White)
    }
}

fun checkInput(input: Int) : Boolean {
    if (input < 50)
        return false
    return true
}
