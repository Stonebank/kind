package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.controller.BottomNavigation
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.secondaryButtonColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.ui.theme.titleColor
import com.dtu.kd3.kind.utility.CategoryTestData
import com.dtu.kd3.kind.views.ComposableView
import kotlinx.coroutines.launch
import java.util.*

/**
 * author s205409 - Hassan Kassem
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowBuildPortFolioView(navController: NavController) {
    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .background(secondaryColor)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier
                    .background(secondaryColor)
                    .fillMaxWidth()) {
                    Image(modifier = Modifier.clickable { navController.navigate(ComposableView.PortfolioView.route) }, painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "")
                    Text(modifier = Modifier.padding(start = 24.dp), text = "Byg din portefølje", color = titleColor, fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center)
                }
                Text(text = "Opbyg din helt egen personlige portfølje", color = Color.Black, fontSize = 16.sp)
                CategoryTestData.values().forEach { value -> ThemeCard(
                    title = value.title,
                    description = value.description
                ) }
            }
        }
    }
}

@Composable
fun ThemeCard(title: String, description: String) {
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
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {

            var popupControl by remember { mutableStateOf(false) }

            if (popupControl) {
                Popup(
                    alignment = Alignment.CenterStart,
                    offset = IntOffset(0, 1500),
                    onDismissRequest = { popupControl = false },
                ) {
                    CharitiesOverview()
                }
            }

            Button(onClick = { /*TODO*/ }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)) {
                Text("Tilføj tema", textAlign = TextAlign.Center, fontSize = 12.sp)
            }

            Button(onClick = {popupControl = true}, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = secondaryButtonColor)) {
                Text("Læs mere", textAlign = TextAlign.Center, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun CharitiesOverview(){
    Box(modifier = Modifier
        .width(250.dp)
        .height(220.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Text(text = "Test", color = titleColor, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Testext", color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }}
}