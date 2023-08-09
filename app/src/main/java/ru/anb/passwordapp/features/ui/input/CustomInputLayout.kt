package ru.anb.passwordapp.features.ui.input

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

abstract class CustomInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr), Validation {

    protected abstract val errorMessageId: Int
    private val textWatcher = RegistrationTextWatcher { error = "" }

    open fun text() = editText?.text.toString()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText?.addTextChangedListener(textWatcher)
    }

    override fun isValid(): Boolean {
        val isValid = innerIsValid()
        error = if (isValid) "" else context.getString(errorMessageId)
        return isValid
    }

    protected abstract fun innerIsValid(): Boolean
}

interface Validation {
    fun isValid(): Boolean
}