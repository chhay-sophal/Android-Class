package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme

class InstagramActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE6E2E1)
                ) {
                    App()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAppInsta(){
    App()
}

@Composable
fun App() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ComposeImageCard()
    }
}

@Composable
fun ComposeImageCard() {
    Surface(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.background(Color(197, 198, 208))
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile picture
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .padding(end = 0.dp)
                ) {

                    AsyncImage(
                        model = "https://images.ctfassets.net/ubrcxzi8y7m2/47pLKWAeoJn0s0ky690ePA/831d9a4f29cbb90703beb157ca3f3aa0/Apple-logo.png",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                // Text
                Text(
                    "Apple",
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = Bold,
                )
                // Spacer to push the icon to the right
                Spacer(modifier = Modifier.weight(1f))
                // Icon on the right
                AsyncImage(
                    model = "https://img.icons8.com/?size=100&id=36944&format=png",
                    contentDescription = "Options",
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            // Image
            Surface(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(Color.Red),
                shape = RoundedCornerShape(0.dp),
            ) {
                AsyncImage(
                    model = "https://media.wired.com/photos/647e2a2040f1b0ff578445a2/master/w_2560%2Cc_limit/Apple-Vision-Pro-Gear.jpg",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            // Button row
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    // Like button
                    AsyncImage(
                        model = "https://img.icons8.com/color/like",
                        contentDescription = "Like",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(23.dp),
                        contentScale = ContentScale.Fit,
                    )
                    // Comment
                    AsyncImage(
                        model = "https://img.icons8.com/?size=100&id=143&format=png",
                        contentDescription = "Comment",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(20.dp),
                        contentScale = ContentScale.Fit,
                    )
                    // Share
                    AsyncImage(
                        model = "https://img.icons8.com/?size=100&id=12582&format=png",
                        contentDescription = "Share",
                        modifier = Modifier.size(20.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
                // Spacer to push the save button to the right
                Spacer(modifier = Modifier.weight(1f))
                // Save button
                AsyncImage(
                    model = "https://img.icons8.com/?size=48&id=82461&format=png",
                    contentDescription = "Save",
                    modifier = Modifier.size(25.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
            ) {
                // Text
                Text(
                    "Apple Vision Pro is a wearable device that transforms your space with apps, entertainment, photos, videos, and more.",
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = Normal,
                )
            }
        }
    }
}