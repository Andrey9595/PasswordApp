package ru.anb.passwordapp.features.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.anb.passwordapp.data.AuthResult
import ru.anb.passwordapp.domain.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _authState = MutableLiveData<AuthResult>()

    val authState: LiveData<AuthResult> get() = _authState

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.postValue(AuthResult.Loading)
            val result = authRepository.signUpWithEmailAndPassword(email, password)
            _authState.postValue(result)
        }
    }
}