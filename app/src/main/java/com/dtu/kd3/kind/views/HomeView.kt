package com.dtu.kd3.kind.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtu.kd3.kind.ui.theme.primaryColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import kotlin.math.roundToInt

/**
 * @author s205409 - Hassan K
 */

@Composable
fun ShowHomeView() {
    val configuration = LocalConfiguration.current
    val splitHeight = (configuration.screenHeightDp / 2).dp
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top), horizontalAlignment = Alignment.End) {
            Box(modifier = Modifier
                .background(primaryColor)
                .height(height = splitHeight)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()) {
                Text("Hej Hassan Kassem",
                    Modifier
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 10.dp), color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                ShowDonatedAmount(amount = 200.0)
                Text(
                    "Du er blandt 1% af top donerer i denne måned. Godt gået!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(vertical = 150.dp)
                        .padding(horizontal = 10.dp))
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                ShowCard()
            }
        }
    }
}

@Composable
fun ShowCard() {
    Box(modifier = Modifier
        .width(250.dp).height(220.dp)
        .background(secondaryColor).shadow(5.dp, shape = RectangleShape)) {
    }
}

@Composable
fun ShowDonatedAmount(amount: Double) {
    Text(
        "Dit abonnement er på plads og du er on track til at donere ${amount.roundToInt()} kr.",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier
            .padding(vertical = 50.dp)
            .padding(horizontal = 10.dp))
}