package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.utility.CategoryTestData
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.controller.BottomNavigation
import com.dtu.kd3.kind.ui.theme.*
import com.dtu.kd3.kind.views.ComposableView
import java.util.*

/**
 * author - s215817 Elias Rajabi
 *
 * Contributors:
 *  - s205409 - Hassan Kassem
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowPortFolioView(navController: NavController) {
    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .background(secondaryColor)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "KD3", color = titleColor, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Image(painter = painterResource(id = R.drawable.stock_profile), contentDescription = "profile_picture", contentScale = ContentScale.Crop, modifier = Modifier
                    .size(120.dp)
                    .clip(
                        CircleShape
                    ))
                Row(modifier = Modifier.fillMaxHeight(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Button(onClick = { navController.navigate(ComposableView.EditProfileView.route)}, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), shape = CircleShape) {
                        Text("Rediger profil")
                    }
                    Button(onClick = { navController.navigate(ComposableView.BuildPortfolioView.route) }, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), shape = CircleShape) {
                        Text("Byg portfølje")
                    }
                }
                CategoryTestData.values().forEach { value -> SubscribedCards(
                    title = value.title
                ) }
            }
        }
    }
}

@Composable
fun SubscribedCards(title: String) {
    Box(modifier = Modifier.height(100.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = title.uppercase(Locale.ROOT), textAlign = TextAlign.Start, color = titleColor, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
            Text(text = "${(1..20).random()}%", color = percentageColor, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), textAlign = TextAlign.End)
        }
    }
}
