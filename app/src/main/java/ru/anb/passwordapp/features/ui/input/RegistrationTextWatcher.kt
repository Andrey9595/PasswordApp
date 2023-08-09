package ru.anb.passwordapp.features.ui.input

import android.text.Editable
import android.text.TextWatcher

class RegistrationTextWatcher(private val onTextChanged: () -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged.invoke()
    }

    override fun afterTextChanged(s: Editable?) {

    }
}