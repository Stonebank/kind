package com.example.kind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.flowermeditationapp.ui.theme.*
import com.breens.flowermeditationapp.ui.utils.*
import com.dtu.kd3.kind.Navigation
import com.dtu.kd3.kind.Screens
import com.example.kind.ui.theme.Grey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowerDonationAppTheme {
                Column(
                    modifier = Modifier
                        .background(Grey)
                        .fillMaxSize()
                ) {

                    HeaderProfileComponent()
                    bygPorteføljeButton()
                    DonationCategoryComponent()
                    NavigationBar()

                    //Screens.signinScreen()
                    //Screens.porteføljeScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    fun HeaderProfileComponent() {
        Row( horizontalArrangement = Arrangement.SpaceBetween,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 0.dp, top = 20.dp)  // porteføjle og description
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

               Column(modifier = Modifier.padding(start = 130.dp)) {
                    Text(
                        text = "Erik Knudsen",
                        fontFamily = nunitoBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start
                    )

                }
            }

        }

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 130.dp, end = 0.dp, top = 0.dp)
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(start = 160.dp),
                shape = RoundedCornerShape(13.dp)

            )

            {
                Text("Edit")
            }

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 30.dp, top = 20.dp)  // porteføjle og description
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier.padding(start = 130.dp)) {
                    Text(
                        text = "Portefølje",
                        fontFamily = nunitoBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start
                    )

                }
            }

        }
        Row(

            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 30.dp, top = 15.dp)  // porteføjle og description
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier.padding(start = 60.dp)) {

                    Text(
                        text = "Oversigt over dine teamer",
                        fontFamily = nunitoBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }

        }

    }
}

@Composable
fun bygPorteføljeButton(){
    Row(modifier = Modifier
            .fillMaxWidth()
        //    .padding(start = 0.dp, end = 20.dp, top = 30.dp)  // porteføjle og description
    ) {


        Button(
            onClick = {},
            modifier = Modifier
                .padding(start = 120.dp, top = 20.dp, bottom = 15.dp),
            shape = RoundedCornerShape(13.dp)

        )

        {
            Text("Byg Portefølje",Modifier.padding(7.dp))
        }
    }
}

@Composable
fun DonationCategoryComponent() {
    val donationOptions = Donation_Category
    LazyColumn(
        Modifier.padding(start = 50.dp, top = 20.dp, end = 50.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(donationOptions.size) {
            DonationOptionComponent(donationTypes = donationOptions[it])
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DonationOptionComponent(donationTypes: Donationcategory) {
    Card(
        // shape = RoundedCornerShape(0.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 30.dp, top = 0.dp, end = 30.dp, bottom = 0.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
                Chip(
                    onClick = { /*TODO*/ },
                    colors = ChipDefaults.chipColors(
                        contentColor = Black,
                        backgroundColor = donationTypes.timeBackgroundColor
                    ),
                    //  shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = donationTypes.category, fontFamily = nunitoBold)
                }


                Chip(
                    onClick = { /*TODO*/ },
                    colors = ChipDefaults.chipColors(
                        contentColor = Color.Black,
                        backgroundColor = Color.White
                    ),
                    //shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = donationTypes.procent, fontFamily = nunitoBold)
                }
            }


        }
    }
}

@Composable
fun NavigationBar() {

    Card(
        // shape = RoundedCornerShape(0.dp),
        modifier = Modifier.padding(start=0.dp,end=0.dp,top=65.dp, bottom = 0.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 80.dp, top = 10.dp, end = 90.dp, bottom = 10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "home",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 0.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.edith),
                    contentDescription = "edith",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 0.dp, top = 0.dp)
                        .size(50.dp)
                        .clip(CircleShape)

                )

            }
        }
    }
}




