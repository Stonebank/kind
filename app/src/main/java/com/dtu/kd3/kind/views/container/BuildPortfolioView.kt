package com.dtu.kd3.kind.views.container

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.controller.BottomNavigation
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.ui.theme.secondaryButtonColor
import com.dtu.kd3.kind.ui.theme.secondaryColor
import com.dtu.kd3.kind.ui.theme.titleColor
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.views.ComposableView
import java.util.*

/**
 * author s205409 - Hassan Kassem
 *
 * Contributors:
 *  - s205409 - Muaz Ahmed
 *
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowBuildPortFolioView(navController: NavController, userViewModel: UserViewModel) {
    var refresh by rememberSaveable { mutableStateOf(false) }
    val localContext = LocalContext.current
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
                if (userViewModel.subscribed.size == ThemeManager.instance.theme.size) {
                    Text(text = "Vi takker dig for din hjælp. Der er ikke flere velgørenhedsmål at vælge imellem", color = Color.Black, fontSize = 16.sp)
                    return@Column
                }
                Text(text = "Opbyg din helt egen personlige portfølje", color = Color.Black, fontSize = 16.sp)
                for (theme in ThemeManager.instance.theme) {
                    if (userViewModel.subscribed.contains(theme))
                        continue
                    ThemeCard(userViewModel = userViewModel, theme = theme, localContext = localContext)
                    refresh = !refresh
                }
            }
        }
    }
}

@Composable
fun ThemeCard(userViewModel: UserViewModel, theme: Theme, localContext: Context) {
    Box(modifier = Modifier
        .width(250.dp)
        .height(220.dp)
        .background(secondaryColor)
        .shadow(1.5.dp, shape = RectangleShape)) {
        Text(text = theme.getName().uppercase(Locale.ROOT), color = titleColor, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = theme.getDescription()[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Button(onClick = {
                                if (userViewModel.subscribed.contains(theme))
                                 return@Button
                                userViewModel.addTheme(theme)
                                Toast.makeText(localContext, "Du abbonnere nu til ${theme.getName()} temaet", Toast.LENGTH_SHORT).show()
            }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)) {
                Text("Tilføj tema", textAlign = TextAlign.Center, fontSize = 12.sp)
            }
            val t = Toast.makeText(localContext, theme.getDescription()[1], Toast.LENGTH_LONG)
            Button(onClick = { t.setGravity(Gravity.FILL_HORIZONTAL, 0, 0); t.show() }, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = secondaryButtonColor)) {
                Text("Læs mere", textAlign = TextAlign.Center, fontSize = 12.sp)
            }
        }
    }
}
