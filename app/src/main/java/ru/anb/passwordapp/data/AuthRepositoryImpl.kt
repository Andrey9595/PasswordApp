package ru.anb.passwordapp.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.anb.passwordapp.domain.AuthRepository
import ru.anb.passwordapp.domain.User
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user!!
            AuthResult.Success(User.Base(user.email ?: " ", user.uid))
        } catch (e: Exception) {
            AuthResult.Error(e)
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user!!
            AuthResult.Success(User.Base(user.email ?: " ", user.uid))
        } catch (e: Exception) {
            AuthResult.Error(e)
        }
    }
}