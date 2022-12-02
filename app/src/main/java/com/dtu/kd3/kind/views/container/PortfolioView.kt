package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.charities.Category
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.controller.BottomNavigation
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.ui.theme.*
import com.dtu.kd3.kind.views.ComposableView
import java.util.*
import kotlin.math.roundToInt

/**
 * author - s215817 Elias Rajabi
 *
 * Contributors:
 *  - s205409 - Hassan Kassem
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowPortFolioView(navController: NavController, userViewModel: UserViewModel) {
    var refresh by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
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
                    Button(onClick = {
                                     if (!userViewModel.donator.value) {
                                         Toast.makeText(context, "Du skal donere et beløb før du kan bygge dit portfølje", Toast.LENGTH_LONG).show()
                                         return@Button
                                     }
                                     navController.navigate(ComposableView.BuildPortfolioView.route)
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), shape = CircleShape) {
                        Text("Byg portfølje")
                    }
                }
                if (userViewModel.subscribed.isEmpty()) {
                    Text(text = "Du har ingen portfølje endnu", color = titleColor, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    return@Column
                }
                for (theme in userViewModel.subscribed) {
                    SubscribedCards(userViewModel, theme, context)
                    refresh = !refresh
                }
            }
        }
    }
}

@Composable
fun SubscribedCards(userViewModel: UserViewModel, theme: Theme, context: Context) {
    Box(modifier = Modifier
        .height(100.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = theme.getName().uppercase(Locale.ROOT), textAlign = TextAlign.Start, color = titleColor, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
            Text(text = "${(100 / userViewModel.subscribed.size).toDouble().roundToInt()}%", color = percentageColor, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), textAlign = TextAlign.End)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                contentDescription = "delete",
                tint = titleColor,
                modifier = Modifier.clickable(onClick = {
                    userViewModel.removeTheme(theme)
                    Toast.makeText(context, "Du har fjernet ${theme.getName()} fra din portfølje", Toast.LENGTH_LONG).show()
                })
            )
        }
    }
}
