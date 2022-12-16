package com.turtleteam.myapp.ui.fragments.auth.loginfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.turtleteam.myapp.R
import com.turtleteam.myapp.data.model.users.UserId
import com.turtleteam.myapp.data.preferences.UserPreferences
import com.turtleteam.myapp.data.wrapper.Result
import com.turtleteam.myapp.databinding.FragmentAuthBinding
import com.turtleteam.myapp.ui.fragments.auth.base.BaseAuthFragment
import com.turtleteam.myapp.utils.ViewAnimations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.android.synthetic.main.fragment_auth.passwordEditText
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class AuthFragment : BaseAuthFragment<FragmentAuthBinding>() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authButton.setOnClickListener {
            ViewAnimations.blackout(false, binding.cardAuth)
            binding.apply {
                authButton.isClickable = false
                registerButton.isClickable = false
                fioEditText.isFocusable = false
                passwordEditText.isFocusable = false
                loadingview.visibility = View.VISIBLE
            }

            viewModel.login(binding.fioEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }

        viewModel.userId.observe(viewLifecycleOwner) {
            handleResult(it)
            ViewAnimations.blackout(true, binding.cardAuth)
            binding.apply {
                authButton.isClickable = true
                registerButton.isClickable = true
                fioEditText.isFocusableInTouchMode = true
                passwordEditText.isFocusableInTouchMode = true
                loadingview.visibility = View.GONE
            }
        }
    }

    private fun handleResult(result: com.turtleteam.myapp.data.wrapper.Result<UserId>) {
        when (result) {
            is Result.Success -> {
                if (result.value.token!=null) {
                    context?.let { UserPreferences(it).getUserId(result.value.token) }
                    Toast.makeText(context, result.value.token, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                }else{
                    handleResult(Result.NotFoundError)
                }
            }
            is Result.Loading -> {
                binding.loadingview.visibility = View.VISIBLE
            }
            is Result.ConnectionError,
            is Result.Error ->{
                Toast.makeText(context, "Не удалось войти в аккаунт", Toast.LENGTH_LONG).show()
            }
            is Result.NotFoundError,
            -> {
                Toast.makeText(context, "Такого аккаунта не существует", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)

}