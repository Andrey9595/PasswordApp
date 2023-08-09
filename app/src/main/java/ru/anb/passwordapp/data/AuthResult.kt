package ru.anb.passwordapp.data

import ru.anb.passwordapp.domain.User

sealed class AuthResult {

    class Success(val user: User) : AuthResult()

    class Error(val e: Exception) : AuthResult()

    object Loading: AuthResult()
}
