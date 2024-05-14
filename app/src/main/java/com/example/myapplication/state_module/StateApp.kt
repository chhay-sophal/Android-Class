package com.example.myapplication.state_module

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.state_module.screens.StateDetailScreen
import com.example.myapplication.state_module.screens.StateHomeScreen
import com.example.myapplication.state_module.viewmodels.CounterViewModel

@Preview(showSystemUi = true)
@Composable
fun StateApp(){

    val counterVM: CounterViewModel = viewModel()

    Column {
        StateHomeScreen(counterVM)
        StateDetailScreen(counterVM)
    }
}
