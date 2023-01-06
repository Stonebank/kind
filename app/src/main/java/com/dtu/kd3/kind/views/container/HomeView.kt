package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.utility.DummyNews
import com.dtu.kd3.kind.views.ComposableView
import java.util.*

/**
 * @author s205409 - Hassan K
 *
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowHomeView(navController: NavController, userViewModel: UserViewModel) {
    Scaffold(bottomBar = { com.dtu.kd3.kind.controller.BottomNavigation(navController = navController) }, modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp), verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.Top)) {
                HomeTitle(userViewModel = userViewModel, navController = navController)
                FeaturedSection()
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.SpaceEvenly) {
                    FeaturedCard(theme = ThemeManager.instance.getTheme("social udsatte"), title = "Abonnere for at støtte de sociale udsatte", tags = listOf("Sult", "Fattigdom", "Børn", "Familie"), image = R.drawable.featured_charity, navController = navController)
                    FeaturedCard(theme = ThemeManager.instance.getTheme("sundhed"), title = "Abonnere for at bekæmpe COVID19 med vaccinationer", tags = listOf("Sygdom", "COVID19", "Corona"), image = R.drawable.featured_charity_2, navController = navController)
                }
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
                    NewsSection()
                    for (news in DummyNews.values()) {
                        NewsCard(title = news.title, description = news.description, url = news.url, image = news.image)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTitle(userViewModel: UserViewModel, navController: NavController) {
    Spacer(modifier = Modifier.height(10.dp))
    Text("Hej ${userViewModel.name.value}", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    Text("${if (userViewModel.donator.value) "Dit abonnement på ${String.format("%.1f", userViewModel.donated.value)} kr. er sat op! Vi takker for at du vil være med til at gøre en forskel." else "Med kun 50 kr. kan du gøre en forskel for en person i nød. "} ", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black.copy(alpha = 0.5f), modifier = Modifier.padding(end = 10.dp))
    if (!userViewModel.donator.value) {
        Button(onClick = {
                            navController.navigate(route = ComposableView.SetDonationView.route)
        }, colors = ButtonDefaults.buttonColors(buttonColor)) {
            Text("Abonner nu", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center)
        }
    }

}

@Composable
fun NewsSection() {
    Spacer(modifier = Modifier.height(30.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 10.dp)) {
        Text("Nyheder", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun FeaturedSection() {
    
    Spacer(modifier = Modifier.height(30.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 10.dp)) {
        Text("Dagens udvalgte temaer", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
    
}

@Composable
fun NewsCard(title: String, description: String, url: String, image: Int) {
    val uriHandler = LocalUriHandler.current
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(end = 10.dp, bottom = 70.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .size(350.dp, 450.dp)
            .shadow(elevation = 10.dp)) {
            Box(modifier = Modifier
                .width(350.dp)
                .height(450.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(secondaryColor)) {
                Image(painter = painterResource(id = image), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
                    .widthIn(350.dp, 350.dp)
                    .heightIn(250.dp, 250.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp, top = 180.dp),

                        )
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, top = 20.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                        Text(text = description, modifier = Modifier.padding(top = 40.dp), fontSize = 16.sp, color = Color.Black.copy(0.5f), maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)) {
                            Button(onClick = {
                                uriHandler.openUri(url)
                            }, colors = ButtonDefaults.buttonColors(buttonColor), elevation = null) {
                                Text("Læs mere", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedCard(theme: Theme, title: String, tags: List<String>, image: Int, navController: NavController) {
    var subscribers by remember { mutableStateOf((1..10_000).random()) }
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(end = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .size(350.dp, 450.dp)
            .shadow(elevation = 10.dp)) {
            Box(modifier = Modifier
                .width(350.dp)
                .height(450.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(secondaryColor)) {
                Image(painter = painterResource(id = image), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
                    .widthIn(350.dp, 350.dp)
                    .heightIn(250.dp, 250.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp, top = 180.dp),
                    )
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, top = 20.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            TagCard(tags = tags)
                        }
                        Text(text = "$subscribers abonnenter", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                            Button(onClick = {
                                             subscribers++
                            }, colors = ButtonDefaults.buttonColors(buttonColor), elevation = null) {
                                Text("Abonnere", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Center)
                            }
                            Button(onClick = {
                                navController.navigate(route = ComposableView.ReadMoreView.passArguments(theme.getName()))
                            }, colors = ButtonDefaults.buttonColors(buttonColor), elevation = null) {
                                Text("Læs mere", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TagCard(tags: List<String>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, top = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        for (i in 0.. tags.size) {
            if (i > 1) {
                val restAmount = tags.size - 2
                Text("+$restAmount", fontSize = 14.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
                    .padding(5.dp))
                break
            }
            Text(
                tags[i], fontSize = 14.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(10.dp))
                    .padding(5.dp))
        }
    }

}