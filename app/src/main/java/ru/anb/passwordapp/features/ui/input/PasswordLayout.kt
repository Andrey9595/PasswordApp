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

    private val binding: PasswordLayoutBinding

    init {
        orientation = VERTICAL
        binding = PasswordLayoutBinding.inflate(LayoutInflater.from(context), this)
        listOf(binding.passwordEditText, binding.passwordRepeatEditText).forEach {
            it.addTextChangedListener(RegistrationTextWatcher {
                binding.errorText.visibility = GONE
            })
        }
    }

    private val errorMessageId: Int = R.string.password_error_same

    override fun isValid(): Boolean {
        val isValid = innerIsValid()
        binding.errorText.text =
            if (isValid) "" else context.getString(errorMessageId)
        return isValid
    }

    private fun innerIsValid(): Boolean {
        with(binding) {
            passwordLayout.isValid()
            passwordRepeatLayout.isValid()
            return passwordLayout.text() ==passwordRepeatLayout.text()
        }
    }

    fun text(): String {
        return binding.passwordLayout.text()
    }
}