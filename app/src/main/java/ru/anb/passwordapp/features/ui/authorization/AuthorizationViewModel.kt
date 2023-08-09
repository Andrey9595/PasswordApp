package ru.anb.passwordapp.features.ui.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.anb.passwordapp.data.AuthResult
import ru.anb.passwordapp.domain.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _authState = MutableLiveData<AuthResult>()

    val authState: LiveData<AuthResult> get() = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _authState.postValue(AuthResult.Loading)
            val result = authRepository.signInWithEmailAndPassword(email, password)
            _authState.postValue(result)
        }
    }
}