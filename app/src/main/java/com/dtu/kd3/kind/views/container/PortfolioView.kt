package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

    var pictureUri by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),)
    { uri: Uri? ->
        uri?.let { pictureUri = it.toString() }
    }

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
                            launcher.launch("image/*")
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
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp), verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.Start) {
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
                        if (userViewModel.subscribed.size > 1) {
                            Button(onClick = { navController.navigate(route = ComposableView.EditPercentageView.route)  }, colors = ButtonDefaults.buttonColors(buttonColor), shape = CircleShape) {
                                Text("Rediger procentfordeling")
                            }
                        }
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

    var dialogVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .height(100.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Image(painter = painterResource(id = theme.getImage()), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxWidth()
            .heightIn(100.dp, 100.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
            contentDescription = "delete",
            tint = Color.Red.copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable(onClick = {
                    dialogVisible = true
                })
        )
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = theme.getName().uppercase(Locale.ROOT), textAlign = TextAlign.Start, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), style = TextStyle(fontSize = 64.sp, shadow = Shadow(color = Color.Black, blurRadius = 5f)))
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
            Text(text = "${userViewModel.getPercentage(theme.getCategory())}%", color = percentageColor, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 17.dp, vertical = 10.dp), textAlign = TextAlign.End, style = TextStyle(fontSize = 64.sp, shadow = Shadow(color = Color.Black, blurRadius = 5f)))
        }
    }

    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false },
            title = { Text(text = "Fjern ${theme.getName()} fra din portfølje?") },
            text = { Text(text = "Dette vil fjerne ${theme.getName()} fra din portfølje og du vil ikke længere abonnere hos dette tema.") },
            confirmButton = {
                Button(onClick = {
                    userViewModel.removeTheme(theme)
                    Toast
                        .makeText(
                            context,
                            "Du har fjernet ${theme.getName()} fra din portfølje",
                            Toast.LENGTH_LONG
                        )
                        .show()
                    dialogVisible = false
                }, colors = ButtonDefaults.buttonColors(buttonColor)) {
                    Text(text = "Fjern")
                }
            },
            dismissButton = {
                Button(onClick = { dialogVisible = false }, colors = ButtonDefaults.buttonColors(Color.Green)) {
                    Text(text = "Fortryd")
                }
            }
        )
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

/**
 * This function returns a list of strings that is used to display the message in the portfolio view.
 * @param userViewModel The userViewModel that is used to get the data from the user.
 * @return A list of strings that is used to display the message in the portfolio view.
 */

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
