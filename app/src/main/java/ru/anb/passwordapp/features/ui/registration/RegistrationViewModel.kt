package ru.anb.passwordapp.features.ui.registration

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.anb.passwordapp.core.ui.BaseViewModel
import ru.anb.passwordapp.data.AuthResult
import ru.anb.passwordapp.domain.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    override val sendRequest: suspend (String, String) -> AuthResult =
        { email, password -> authRepository.signUpWithEmailAndPassword(email, password) }

}