package ru.anb.passwordapp.domain

import ru.anb.passwordapp.data.AuthResult

interface AuthRepository {

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult

    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult
}