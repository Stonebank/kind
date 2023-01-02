package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.ui.theme.*
import com.dtu.kd3.kind.views.ComposableView
import java.util.*
import kotlin.math.roundToInt

/**
 * author - s205409 - Hassan Kassem
 *
 * Contributors:
 *  - s215817 Elias Rajabi
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowPortFolioView(navController: NavController, userViewModel: UserViewModel) {
    val context = LocalContext.current
    var refresh by remember { mutableStateOf(false) }
    Scaffold(bottomBar = { com.dtu.kd3.kind.controller.BottomNavigation(navController = navController) }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)) {
                    Text(text = userViewModel.name.value, color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 70.dp, top = 20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {
                        RoundedProfileImage(imageID = R.drawable.dtu_logo)
                        IconButton(onClick = {
                            Toast.makeText(context, "THIS FEATURE HAS NOT BEEN ADDED YET!", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "edit", tint = Color.Black)
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                        Button(onClick = { navController.navigate(ComposableView.EditProfileView.route)}, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), shape = CircleShape) {
                            Text("Rediger profil")
                        }
                        Button(onClick = {
                            navController.navigate(ComposableView.BuildPortfolioView.route)
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), shape = CircleShape) {
                            Text("Byg portfølje")
                        }
                    }
                    Column(modifier = Modifier.fillMaxSize().padding(bottom = 50.dp), verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.Start) {
                        Text(
                            portfolioMessage(userViewModel = userViewModel)[0],
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                        Text(
                            portfolioMessage(userViewModel = userViewModel)[1],
                            modifier = Modifier.padding(start = 5.dp),
                            color = Color.Black.copy(0.5f),
                            fontSize = 14.sp,
                        )
                        if (userViewModel.subscribed.isNotEmpty()) {
                            userViewModel.subscribed.forEach { theme -> SubscribedCards(
                                userViewModel = userViewModel,
                                theme = theme,
                                context = context
                            )
                            refresh = !refresh
                            }
                        }
                    }
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
        Image(painter = painterResource(id = theme.getImage()), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxWidth().heightIn(100.dp, 100.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = theme.getName().uppercase(Locale.ROOT), textAlign = TextAlign.Start, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
            Text(text = "${(100 / userViewModel.subscribed.size).toDouble().roundToInt()}%", color = percentageColor, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), textAlign = TextAlign.End)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                contentDescription = "delete",
                tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    userViewModel.removeTheme(theme)
                    Toast.makeText(context, "Du har fjernet ${theme.getName()} fra din portfølje", Toast.LENGTH_LONG).show()
                })
            )
        }
    }
}

@Composable
fun RoundedProfileImage(imageID: Int) {
    Image(
        painter = painterResource(id = imageID),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Transparent, CircleShape)
    )
}

fun portfolioMessage(userViewModel: UserViewModel) : List<String> {
    val message = mutableListOf<String>()
    if (userViewModel.subscribed.isNotEmpty() && !userViewModel.donator.value) {
        message.add("Oversigt")
        message.add("Du har valgt ${userViewModel.subscribed.size} forskellige temaer. Abonnere nu for at støtte dem!")
        return message
    }
    if (userViewModel.subscribed.isNotEmpty() && userViewModel.donator.value) {
        message.add("Oversigt")
        message.add("Du kan til enhver tid stoppe med at støtte dine valgte temaer. Nye temeaer vil først blive trukket den første i hver måned.")
        return message
    }
    message.add("Du har ingen portfølje endnu")
    message.add("Byg dit portfølje ved at abonnere med et beløb og derefter vælge en af mange velgørende organisationer.")
    return message
}
