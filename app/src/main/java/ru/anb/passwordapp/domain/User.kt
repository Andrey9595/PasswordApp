package ru.anb.passwordapp.domain

abstract class User {

    abstract val email: String
    abstract val id: String

    class Base(override val email: String, override val id: String) : User()

    object Empty: User() {
        override val email = "Empty"
        override val id = "Empty_id"
    }
}
