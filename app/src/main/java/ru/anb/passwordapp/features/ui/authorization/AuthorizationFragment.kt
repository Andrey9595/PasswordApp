package ru.anb.passwordapp.features.ui.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.passwordapp.R
import ru.anb.passwordapp.core.ui.BaseFragment
import ru.anb.passwordapp.data.AuthResult
import ru.anb.passwordapp.databinding.FragmentAuthorizationBinding

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentAuthorizationBinding =
        { inflater, container ->
            FragmentAuthorizationBinding.inflate(inflater, container, false)
        }

    private val viewModel: AuthorizationViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputList = listOf(
            binding.authMail,
            binding.authPassword
        )

        viewModel.authState.observe(viewLifecycleOwner) {
            if (it is AuthResult.Loading)
                binding.progressBar.visibility = View.VISIBLE
            if (it is AuthResult.Error) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), it.e.message.toString(), Toast.LENGTH_LONG).show()
//            if (it is AuthResult.Success)
//                findNavController().navigate()
            }
        }

        binding.signIn.setOnClickListener {
            val allValidation = inputList.map { it.isValid() }

            if (allValidation.all { it }) {
                viewModel.signIn(
                    email = binding.authMail.text(),
                    password = binding.authPassword.text()
                )
            }
        }
        binding.navigateToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }

    }
}