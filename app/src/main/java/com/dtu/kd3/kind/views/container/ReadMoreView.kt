package com.dtu.kd3.kind.views.container

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.model.charities.Theme
import com.dtu.kd3.kind.model.charities.ThemeManager
import com.dtu.kd3.kind.model.charities.container.Environment
import com.dtu.kd3.kind.model.charities.container.Health
import com.dtu.kd3.kind.model.charities.container.Immigrants
import com.dtu.kd3.kind.model.charities.container.Social

@Composable
fun ShowReadMoreView(navController: NavController, theme: Theme) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(75.dp))
            Image(painter = painterResource(id = theme.getImage()), contentDescription = "image", contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxWidth()
                .heightIn(120.dp, 120.dp),  colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f), blendMode = BlendMode.Darken))
            Text(text = theme.getName(), fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = theme.getDescription(), fontSize = 18.sp, fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Here is a list of organizations that you donate to:", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                theme.getOrganisations().forEach {
                    Text(text = "• $it", fontSize = 19.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ReadMorePreview() {
    ThemeManager.instance.theme.add(Social())
    ThemeManager.instance.theme.add(Environment())
    ThemeManager.instance.theme.add(Health())
    ThemeManager.instance.theme.add(Immigrants())
    ShowReadMoreView(navController = rememberNavController(), theme = ThemeManager.instance.theme[0])
}
