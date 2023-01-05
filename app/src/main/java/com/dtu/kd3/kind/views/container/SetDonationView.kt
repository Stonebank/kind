package com.dtu.kd3.kind.views.container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.dtu.kd3.kind.views.ComposableView
import kotlin.math.roundToInt

/**
 * author s205430 - Muaz Ahmed && s205409 - Hassan Kassem
 */



@Composable
fun ShowSetDonationView(navController: NavController, userViewModel: UserViewModel) {
    var input by remember { mutableStateOf(50f) }
    var error by remember { mutableStateOf(false) }
    var editmanually by remember { mutableStateOf(false) }

    input.roundToInt()
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
                Spacer(modifier = Modifier.height(5.dp))
                // "Dette er beløbet som du vil donere hver måned. Du kan ændre det til enhver tid og der er ingen binding. Minimum beløb er 50 kr."
                Text(text = "Dette er beløbet som du vil donere enten som et engangsbeløb eller månedligt. Minimum beløb er 50 kr.", color = Color.Black.copy(0.5f), fontSize = 14.sp, fontWeight = FontWeight.Bold)
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
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    if(editmanually) {
                        TextField(
                            value = input.roundToInt().toString(),
                            onValueChange = { input = it.toFloatOrNull() ?: 0f },
                            label = { Text("Beløb", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
                            placeholder = { Text("0 kr", color = Color.White.copy(0.5f), fontWeight = FontWeight.Bold) },
                            colors = TextFieldDefaults.textFieldColors(Color.Black),
                            singleLine = true,
                            isError = error,
                            keyboardActions = KeyboardActions(onDone = { checkInput(input = input) }),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.size(width = 125.dp, height = 55.dp)
                        )
                        IconButton(onClick = { editmanually = false }) {
                            Icon(Icons.Default.Done, contentDescription = "", tint = Color(0XFF023020), modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Green.copy(0.2f))
                                .size(20.dp))
                        }
                    } else {
                        Text(text = String.format("%.1f", input) + "kr.", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        IconButton(onClick = { editmanually = true }) {
                            Icon(Icons.Default.Edit, contentDescription = "", tint = Color(0XFF0C77C2), modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0XFF0C77C2).copy(0.2f))
                                .size(20.dp))
                        }
                    }

                }

                //Text(text = "50,00kr", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            if (error) {
                Text(
                    text = if (input < 50) "Beløbet skal være på minimum 50 kr." else "", modifier = Modifier.padding(start = 20.dp, top = 5.dp),
                    color = colors.error,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 10.dp, end = 20.dp)
                    .clip(shape = RoundedCornerShape(corner = CornerSize(30.dp)))
                    .background(Color.White)
                    .border(
                        1.dp,
                        Color.Gray,
                        shape = RoundedCornerShape(corner = CornerSize(30.dp))
                    )
                    //.shadow(2.dp, RoundedCornerShape(corner = CornerSize(30.dp)))

            ) {
                Slider(value = input, modifier = Modifier.padding(20.dp), valueRange = 50f..1000f, onValueChange = { input = it }, colors = SliderDefaults.colors(thumbColor = Color(0XFF0C77C2), activeTrackColor = Color(0XFF489EDB) ),  )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier .padding(10.dp) .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    if (input < 50) {
                        error = true
                        return@Button
                    }
//                    userViewModel.setDonation(input.toDouble())
//                    userViewModel.setDonator(true)
                    navController.navigate(route = ComposableView.ConfirmDonationView.passArguments(String.format("%.1f", input)))
                }, colors = ButtonDefaults.buttonColors(buttonColor), elevation = null) {
                    Text("Bekræft beløb", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp,)
                }
            }

        }
    }
}

fun checkInput(input: Float) : Boolean {
    return input >= 50
}

@Preview(showBackground = true)
@Composable
fun DonationPreview() {
    ShowSetDonationView(navController = rememberNavController(), userViewModel = UserViewModel())
}
