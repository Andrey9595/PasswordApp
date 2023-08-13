package ru.anb.passwordapp.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.anb.passwordapp.data.AuthResult

abstract class BaseViewModel : ViewModel() {

    abstract val sendRequest: suspend (String, String) -> AuthResult

    private val _authState = MutableLiveData<AuthResult>()

    val authState: LiveData<AuthResult> get() = _authState

    fun sendCredentials(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.postValue(AuthResult.Loading)
            val result = sendRequest.invoke(email, password)
            _authState.postValue(result)
        }
    }
}