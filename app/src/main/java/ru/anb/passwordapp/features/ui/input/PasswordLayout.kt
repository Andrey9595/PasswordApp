package ru.anb.passwordapp.features.ui.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.anb.passwordapp.R
import ru.anb.passwordapp.databinding.PasswordLayoutBinding

class PasswordLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Validation {

    private val binding = PasswordLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
        listOf(binding.passwordEditText, binding.passwordRepeatEditText).forEach {
            it.addTextChangedListener(RegistrationTextWatcher {
                binding.errorText.text = ""
            })
        }
    }

    private val errorMessageId: Int = R.string.password_error_same

    override fun isValid(): Boolean {
        with(binding) {
            val isPasswordsEquals = passwordLayout.text() == passwordRepeatLayout.text()
            errorText.text = if (isPasswordsEquals) "" else context.getString(errorMessageId)
            val isPasswordsValid = listOf(passwordLayout, passwordRepeatLayout).map { it.isValid() }
            return isPasswordsValid.all { it } && isPasswordsEquals
        }
    }

    fun text(): String {
        return binding.passwordLayout.text()
    }
}