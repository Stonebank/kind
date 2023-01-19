package com.dtu.kd3.kind.views.container

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dtu.kd3.kind.model.UserViewModel
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.views.ComposableView


/**
 * author s205409 - Hassan Kassem
 *
 * Contributors:
 *  - s205409 - Muaz Ahmed
 *
 */


@Composable
fun ShowBuildPortFolioView(navController: NavController, userViewModel: UserViewModel) {
    val gridState = rememberLazyGridState()
    var refresh by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Text(if (isSupportingAll(userViewModel)) "Tak for din støtte!" else "Byg dit portfølje", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(if (isSupportingAll(userViewModel)) "Tusind tak din støtte. Vi tilføjer flere temaer løbende!" else "Find adskillige af velgørenhedstemaer og vælg dem du ønsker at støtte", color = Color.Black.copy(0.5f), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(ThemeManager.instance.theme.size) { index ->
                        ThemeManager.instance.theme[index].let { theme ->
                            if (userViewModel.subscribed.contains(theme))
                                return@let
                            ThemeCard(navController = navController, userViewModel, theme)
                            refresh = !refresh
                        }
                    }
                }, state = gridState
            )
        }
    }
}

@Composable
fun ThemeCard(navController: NavController, userViewModel: UserViewModel, theme: Theme) {
    val localContext = LocalContext.current
    Box(
        modifier = Modifier
            .width(500.dp)
            .height(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black)
    ) {
        Image(painter = painterResource(id = theme.getImage()), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
            .widthIn(500.dp, 500.dp)
            .heightIn(200.dp, 200.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = {
                navController.navigate(route = ComposableView.ReadMoreView.passArguments(theme.getName()))
            }) {
                Icon(Icons.Default.Info, contentDescription = "", tint = Color.White.copy(0.8f), modifier = Modifier.size(35.dp))
            }
            IconButton(onClick = {
                if (userViewModel.subscribed.contains(theme)) {
                    Toast.makeText(localContext, "${theme.getName()} er allerede i dit portfølje", Toast.LENGTH_SHORT).show()
                    return@IconButton
                }
                userViewModel.addTheme(theme)
                Toast.makeText(localContext, "${theme.getName()} er blevet tilføjet til dit portfølje", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Add, contentDescription = "", tint = Color.White, modifier = Modifier.size(35.dp))
            }

        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = theme.getName(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 5.dp, top = 130.dp))
        }
    }
}

/**
 * IsSupportingAll checks if the user is supporting all the themes
 *
 * @param userViewModel the userViewModel
 * @return true if the user is supporting all the themes
 *
 */

fun isSupportingAll(userViewModel: UserViewModel) : Boolean {
    return userViewModel.subscribed.size == ThemeManager.instance.theme.size
}
