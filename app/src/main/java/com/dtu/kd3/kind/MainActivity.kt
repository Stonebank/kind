package com.example.kind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import com.example.kind.ui.theme.Grey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowerMeditationAppTheme {
                Column(
                    modifier = Modifier
                        .background(Grey)
                        .fillMaxSize()
                ) {
                    HeaderProfileComponent()
                    DonationCategoryComponent()
                }
            }
        }
    }

    @Preview
    @Composable
    fun HeaderProfileComponent() {
        Image(
            painter = painterResource(id = R.drawable.left),
            contentDescription = "left",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 15.dp, end = 30.dp, top = 30.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 30.dp, top = 20.dp)  // porteføjle og description
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier.padding(start = 15.dp)) {
                    Text(
                        text = "Portefølje",
                        fontFamily = nunitoLight,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Description",
                        fontFamily = nunitoMedium,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }

        }
    }
}


@Composable
fun DonationCategoryComponent() {
    val donationOptions = Donation_Category
    LazyColumn(
        Modifier.padding(start = 50.dp, top = 80.dp, end = 50.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
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
            modifier = Modifier.padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(55.dp)) {
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

