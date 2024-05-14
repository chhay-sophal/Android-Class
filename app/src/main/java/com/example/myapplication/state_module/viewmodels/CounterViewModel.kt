package com.example.myapplication.state_module.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private var counter by mutableIntStateOf(0)

    fun increase() {
        counter++
    }

    fun decrease() {
        counter--
    }

}