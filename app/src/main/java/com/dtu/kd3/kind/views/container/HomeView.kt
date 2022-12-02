package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.primaryColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.ui.theme.titleColor
import com.dtu.kd3.kind.views.ComposableView
import java.util.*

/**
 * @author s205409 - Hassan K
 *
 * Passing argument email is temporarily since this application has no real functionality and is just for demonstration. Argument should be a name instead.
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowHomeView(navController: NavController, userViewModel: UserViewModel) {
    val configuration = LocalConfiguration.current
    val splitHeight = (configuration.screenHeightDp / 2).dp

    Scaffold(bottomBar = { com.dtu.kd3.kind.controller.BottomNavigation(navController = navController) }) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top), horizontalAlignment = Alignment.End) {
                Box(modifier = Modifier
                    .background(primaryColor)
                    .height(height = splitHeight)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()) {
                    Text("Hej ${userViewModel.name.value}",
                        Modifier
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 10.dp), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        if (userViewModel.donator.value) {
                            Text(
                                "Dit abonnement er på plads og du er on track til at donere ${userViewModel.donated.value.toInt()} kr.",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(vertical = 50.dp)
                                    .padding(horizontal = 10.dp)
                            )
                            Text(
                                "Du er blandt 1% af top donerer i denne måned. Godt gået!",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(vertical = 150.dp)
                                    .padding(horizontal = 10.dp)
                            )
                        } else {
                            Text(
                                "Vi mangler din hjælp! Start med at donere i dag ved at trykke på knappen nedenunder",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(vertical = 50.dp)
                                    .padding(horizontal = 10.dp)
                            )
                        }
                        Button(onClick = {
                            navController.navigate(route = ComposableView.SetDonationView.route)
                            //userViewModel.setDonator(true)
                            /*userViewModel.setDonation((50..1000).random().toDouble())*/}, shape = CircleShape, colors = ButtonDefaults.buttonColors(
                            Color.Green)) {
                            Text("Start", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    UpdateCard(title = "Rød kors", description = "Eksempel update....")
                    UpdateCard(title = "Europa", description = "Mange mangler hjælp...")
                    UpdateCard(title = "Udlændinge", description = "Et eller andet relevant")
                }
            }
        }
    }
}

@Composable
fun UpdateCard(title: String, description: String) {
    Box(modifier = Modifier
        .width(250.dp)
        .height(220.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Text(text = title.uppercase(Locale.ROOT), color = titleColor, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = description.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }
}
