package com.dtu.kd3.kind.views.container

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.ui.theme.buttonColor

/**
 * Created by Hassan on 05-01-2023
 * S205409
 * *Stonebank
 */

@Composable
fun ShowEditPercentageView(navController: NavController, userViewModel: UserViewModel) {
    val localContext = LocalContext.current
    var error by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = {
                if (canSavePercentages(userViewModel = userViewModel)) {
                    Toast.makeText(localContext, "Du har ikke gemt dine ændringer.", Toast.LENGTH_SHORT).show()
                }
                if (!canSavePercentages(userViewModel) || !isNotHundredPercentage(userViewModel)) {
                    println("Percentages fail. Reverted to previous state.")
                }
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(start = 20.dp, end = 10.dp)) {
                Text(
                    text = "Rediger procentfordelingen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(text = "Du kan redigere procentfordelingen på tværs af dine temaer her", fontSize = 14.sp, color = Color.Black.copy(0.5f))
                Spacer(modifier = Modifier.height(20.dp))
                userViewModel.subscribed.forEach { theme ->
                    InputDialog(userViewModel = userViewModel, theme = theme)
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (error) {
                    if (!canSavePercentages(userViewModel))
                        Text("Du skal have en procentfordeling på 100%", color = Color.Red, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    if (!isNotHundredPercentage(userViewModel))
                        Text("Du skal have en procentfordeling på mindst 100%", color = Color.Red, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Button(onClick = {
                                 if (!isNotHundredPercentage(userViewModel)) {
                                     error = true
                                     return@Button
                                 }
                                 if (!canSavePercentages(userViewModel)) {
                                     error = true
                                     return@Button
                                 }
                                 navController.popBackStack()
                                 Toast.makeText(localContext, "Procentfordelingen er gemt", Toast.LENGTH_SHORT).show()
                }, colors = ButtonDefaults.buttonColors(buttonColor)) {
                    Text("Gem", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun InputDialog(userViewModel: UserViewModel, theme: Theme, context: Context = LocalContext.current) {
    var input by remember { mutableStateOf(0) }
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = theme.getName(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("Du støtter temaet med ${userViewModel.getPercentage(theme.getCategory())}%", color = Color.Black.copy(0.5f))
        }
        TextField(
            value = input.toString(),
            onValueChange = {
                            if (it.isNotEmpty()) {
                                input = it.toInt()
                                userViewModel.setPercentage(theme.getCategory(), input)
                            }
            },
            label = { Text(text = "Procent", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
            placeholder = { Text(text = "fx 20", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
            modifier = Modifier.width(100.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Black.copy(0.2f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                textColor = Color.Black
            ),
            keyboardActions = KeyboardActions(onDone = {
                userViewModel.setPercentage(theme.getCategory(), input)
                Toast.makeText(context, "Procenten er gemt", Toast.LENGTH_SHORT).show()
            }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

/**
 * canSavePercentages checks if the percentages are valid
 *
 * @param userViewModel the userViewModel
 *
 */

fun canSavePercentages(userViewModel: UserViewModel) : Boolean {
    var sum = 0
    for (theme in userViewModel.subscribed) {
        sum += userViewModel.getPercentage(theme.getCategory())
    }
    if (sum > 100) {
        userViewModel.subscribed.forEach { theme -> userViewModel.setPercentage(theme.getCategory(), 100 / userViewModel.subscribed.size) }
        return false
    }
    return true
}

/**
 * isNotHundredPercentage checks if the percentages are valid
 *
 * @param userViewModel the userViewModel
 *
 */

fun isNotHundredPercentage(userViewModel: UserViewModel) : Boolean {
    var sum = 0
    for (theme in userViewModel.subscribed) {
        sum += userViewModel.getPercentage(theme.getCategory())
    }
    if (sum < 100) {
        userViewModel.subscribed.forEach { theme -> userViewModel.setPercentage(theme.getCategory(), 100 / userViewModel.subscribed.size) }
        return false
    }
    return true
}

