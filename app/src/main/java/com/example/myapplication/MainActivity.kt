package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.models.movieList1
import com.example.myapplication.screens.DetailScreen
import com.example.myapplication.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE6E2E1)
                ) {
                    ComposableLanguage();
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun App() {
    ComposableLanguage();
}

@Composable
fun ComposableLanguage() {
    var languageName by remember {
        mutableStateOf("English")
    }

    val language: Language = if (languageName == "English") {
        English()
    } else {
        Khmer()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Row {
                Button(onClick = { languageName = "English" }) {
                    Text(text = "English")
                }
                Button(onClick = { languageName = "Khmer" }) {
                    Text(text = "ខ្មែរ")
                }
            }
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = language.home())
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = language.service())
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = language.product())
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = language.buy())
                }
            }
        }
    }
}

interface Language {
    fun home(): String
    fun product(): String
    fun service(): String
    fun buy(): String
}

class English() : Language {
    override fun home(): String = "Home"

    override fun product(): String = "Product"

    override fun service(): String = "Service"

    override fun buy(): String = "Buy"
}

class Khmer() : Language {
    override fun home(): String = "ទំព័រដើម"

    override fun product(): String = "ផលិតផល"

    override fun service(): String = "សេវាកម្ម"

    override fun buy(): String = "ទិញ"
}

@Composable
fun ComposeCounterText() {
    var count by remember {
        mutableIntStateOf(0)
    }

    var dark by remember {
        mutableStateOf(false)
    }

    var fontSize by remember {
        mutableIntStateOf(10);
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (dark) {
                    Color.Black
                } else {
                    Color.White
                }
            )
            .padding(10.dp)
    ) {
        Column {
            Row {
                Button(onClick = { dark = !dark }) {
                    Text(text = "Set to ${if (dark) { "light" } else {"dark"}}")
                }
                Button(onClick = { fontSize -= 5 }) {
                    Text("-")
                }
                Button(onClick = { fontSize += 5 }) {
                    Text("+")
                }
            }
            Text(
                "Cambodia is a Southeast Asian nation whose landscape spans low-lying plains, the Mekong Delta, mountains and Gulf of Thailand coastline. Phnom Penh, its capital, is home to the art deco Central Market, glittering Royal Palace and the National Museum's historical and archaeological exhibits.",
                fontSize = fontSize.sp,
                color = if (dark) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        }
    }
}

@Composable
fun ComposeNavScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(navController = navController)
        }
        composable(
            "detail/{movieName}",
            arguments = listOf(navArgument("movieName") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieName = backStackEntry.arguments?.getString("movieName")
            val selectedMovie = movieList1.first {it.name == movieName}
            DetailScreen(navController, selectedMovie);
        }
    }
}








//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun PreviewApp() {
//    ComposeText()
//}
//
//@Composable
//fun App() {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize(),
//
//    ){
//        AsyncImage(
//            model = "https://fr.web.img5.acsta.net/pictures/24/02/12/14/23/4546780.jpg",
//            contentDescription = "some image",
//            )
//    }
//}
//
//val gradientColors = listOf(Magenta, Yellow, Blue)
//
//@Composable
//fun ComposeText() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(
//            text = "ឆាយ \nសុផល \nChhay \nSophal",
//            fontSize = 100.sp,
//            fontStyle = FontStyle.Italic,
//            fontWeight = FontWeight.Bold,
//            style = TextStyle(
//                textAlign = TextAlign.Center,
//                brush = Brush.linearGradient(
//                    colors = gradientColors
//                )
//            )
//            )
//    }
//}
//
