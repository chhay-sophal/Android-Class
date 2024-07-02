package com.example.myapplication.rnduser

import RandomUserViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUserView(vm: RandomUserViewModel) {
    LaunchedEffect(Unit) { vm.getUsers() }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("RandomUser List") }, actions = {
                IconButton(onClick = { vm.getUsers() }) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { RndUserBody(vm) }
    }
}

@Composable
fun RndUserBody(vm: RandomUserViewModel) {
    if (vm.isLoading) {
        CircularProgressIndicator()
    } else if (vm.errorMessage.isNotEmpty()) {
        Text(vm.errorMessage)
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vm.userList.size){
                RndUserItem(vm.userList[it])
            }
        }
    }
}

@Composable
fun RndUserItem(user: UserResult) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .size(45.dp)
                .background(Color.Gray)
        ) {
            AsyncImage(
                model = "${user.picture.large}",
                contentDescription = null
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Text("${user.name.first} ${user.name.last}")
            Text("${user.gender}")
        }
    }
}
