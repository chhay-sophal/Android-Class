package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun LoopWithoutCoroutine() {
    var isLoading by remember {
        mutableStateOf(false)
    }
    var data by remember {
        mutableLongStateOf(0)
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {
                Toast.makeText(context, "LMAO", Toast.LENGTH_LONG).show()
            }) {
                Text("Show Toast")
            }

            Button(onClick = {
                isLoading = true
                coroutineScope.launch {
                    data = runLoop(1_000)
                    isLoading = false
                }
            }) {
                Text("Run Loop")
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
            Text("Result = $data")
        }
    }
}

suspend fun runLoop(max: Long = 1000L): Long {
    var total: Long = 0
    for (index: Long in 1..max) {
        total += index
        delay(5)
    }
    return total
}

//fun main() = runBlocking { // this: CoroutineScope
//    launch { // launch a new coroutine and continue
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//}