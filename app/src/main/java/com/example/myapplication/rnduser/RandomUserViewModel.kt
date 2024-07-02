import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.rnduser.RandomUsersAPIService
import com.example.myapplication.rnduser.UserResult
import kotlinx.coroutines.launch

class RandomUserViewModel : ViewModel() {
    private val _userList = mutableStateListOf<UserResult>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val userList: List<UserResult>
        get() = _userList

    fun getUsers() {
        viewModelScope.launch {
            isLoading = true
            val apiService = RandomUsersAPIService.getInstance()
            try {
                _userList.clear()
                _userList.addAll(apiService.getUsers(10).results)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
}