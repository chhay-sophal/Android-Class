package com.example.aibfriday.todo_api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@Preview(showBackground = true)
@Composable
fun TodoApp(){
    val vm = TodoViewModel()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        TodoView(vm)
    }
}

@Composable
fun TodoView(vm: TodoViewModel){
    LaunchedEffect(Unit){
        vm.getTodoList()
    }

    if(vm.isLoading){
        CircularProgressIndicator()
    } else if (vm.errorMessage.isNotEmpty()){
        Text("Error: ${vm.errorMessage}")
    } else{
        LazyColumn {
            items(vm.todoList.size){
                val item: TodoModel = vm.todoList[it]
                Text(item.title)
                Divider()
            }

        }
    }
}


data class TodoModel(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)

class TodoViewModel : ViewModel() {
    private val _todoList = mutableStateListOf<TodoModel>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val todoList: List<TodoModel>
        get() = _todoList

    fun getTodoList() {
        viewModelScope.launch {
            isLoading = true
            val apiService = TodoService.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll(apiService.getTodoList())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
}

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface TodoService {
    @GET("todos")
    suspend fun getTodoList(): List<TodoModel>

    companion object {
        var service: TodoService? = null

        fun getInstance(): TodoService {
            if (service == null) {
                service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TodoService::class.java)
            }
            return service!!
        }
    }
}