package com.example.myapplication

import android.R.attr.data
import android.R.attr.radius
import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.theme.MyApplicationTheme


class ColumnAndGridActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE6E2E1)
                ) {
                    ComposeFacebookStory()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewColGrid() {
    ComposeFacebookStory()
}

@Composable
fun ComposeColumn() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(30) {
            Text(
                text = "Item $it",
                modifier = Modifier
                    .padding(2.dp)
                    .background(
                        color = if (it % 2 == 0) Color.Gray else Color.White
                    )
                )
        }
    }
}

var colorList = listOf<Color>(
    Color.Red,
    Color.Green,
    Color.Blue,
    Color.Yellow,
    Color.Magenta,
    Color.Cyan
)

val photos = listOf<String>(
    "https://fr.web.img5.acsta.net/pictures/24/02/12/14/23/4546780.jpg",
    "https://upload.wikimedia.org/wikipedia/en/7/74/The_Wolverine_posterUS.jpg",
    "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UY1200_CR90,0,630,1200_AL_.jpg",
    "https://fr.web.img5.acsta.net/pictures/24/02/12/14/23/4546780.jpg",
    "https://upload.wikimedia.org/wikipedia/en/7/74/The_Wolverine_posterUS.jpg",
    "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UY1200_CR90,0,630,1200_AL_.jpg",
    "https://fr.web.img5.acsta.net/pictures/24/02/12/14/23/4546780.jpg",
    "https://upload.wikimedia.org/wikipedia/en/7/74/The_Wolverine_posterUS.jpg",
    "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UY1200_CR90,0,630,1200_AL_.jpg",
)

@Composable
fun ComposeInstagramStory() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos.size) { index ->
            CirclePhoto(imageUrl = photos[index])
        }
    }
}

@Composable
fun CirclePhoto(imageUrl: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Red, Color.Blue)
                ),
                shape = CircleShape
            ),
    ) {
        Surface(
            modifier = Modifier
                .padding(7.dp),
            color = Color.Black,
            shape = CircleShape
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun ComposeFacebookStory() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(photos.size) {
            index -> RectanglePhotos(imageUrl = photos[index])
        }
    }
}

@Composable
fun RectanglePhotos(imageUrl: String) {
    Box {
        Surface(
        modifier = Modifier
                .padding(7.dp)
                .size(90.dp, 180.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = Color.Black,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}