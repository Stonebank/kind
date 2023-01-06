package com.dtu.kd3.kind.views.container

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dtu.kd3.kind.model.UserViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dtu.kd3.kind.R
import com.dtu.kd3.kind.ui.theme.buttonColor
import com.dtu.kd3.kind.views.ComposableView

/**
 * author s205430 - Muaz Ahmed
 */

@Composable
fun ShowConfirmDonationView(amount: String, navController: NavController, userViewModel: UserViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(Color.White)
            .padding(start = 15.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Image(
                    painter = painterResource(id = R.drawable.donate_icon),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .widthIn(100.dp, 100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .heightIn(100.dp, 100.dp),
                    colorFilter = ColorFilter.tint(
                        Color(0xFFD9F0F7).copy(alpha = 0.4f),
                        blendMode = BlendMode.Darken
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()) {
                    Text(
                        text = "Donér og hjælp med at gøre en forskel",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "$amount kr.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF2D2D2D)
                    )
                    Text(
                        text = "One time payment.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF2D2D2D)
                    )
                }
            }

            Divider(Modifier.padding(end = 14.dp), thickness = 2.dp, color = Color.Black)
            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(text = "Payment Method", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier
                .fillMaxWidth()) {
                ShowPaymentMethod(image = R.drawable.google_pay, method = "Google Pay", userViewModel = userViewModel)
                ShowPaymentMethod(image = R.drawable.credit_card, method = "Credit Card", userViewModel = userViewModel)
                ShowPaymentMethod(image = R.drawable.paypal3, method = "PayPal", userViewModel = userViewModel)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Total", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "$amount kr.", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            Button(onClick = { userViewModel.setDonation(amount.toDouble()); userViewModel.setDonator(true); navController.navigate(
                ComposableView.HomeView.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)) {
                Text(text = "Confirm Donation", color = Color.Black)
            }
        }
    }
}

@Composable
fun ShowPaymentMethod(image: Int, method: String, userViewModel: UserViewModel) {

    Row(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "image",
            contentScale = ContentScale.Fit ,
            modifier = Modifier
                .width(30.dp)
                .height(20.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = method,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2D2D2D)
        )
        Row(modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            RadioButton(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(20.dp),
                selected = (method == userViewModel.paymentmethod.value),
                onClick = { userViewModel.setPaymentMethod(method) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0XFF0C77C2)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShowConfirmDonationView(amount = "50", rememberNavController(), UserViewModel())
}