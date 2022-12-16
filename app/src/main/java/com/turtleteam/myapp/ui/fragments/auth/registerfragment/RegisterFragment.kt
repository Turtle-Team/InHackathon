package com.turtleteam.myapp.ui.fragments.auth.registerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import android.widget.PopupMenu

import androidx.navigation.fragment.findNavController
import com.turtleteam.myapp.R
import com.turtleteam.myapp.data.model.users.AuthRequestBody
import com.turtleteam.myapp.data.model.users.UserId
import com.turtleteam.myapp.data.preferences.UserPreferences
import com.turtleteam.myapp.data.wrapper.Result
import com.turtleteam.myapp.databinding.FragmentRegisterBinding
import com.turtleteam.myapp.ui.fragments.auth.base.BaseAuthFragment
import com.turtleteam.myapp.utils.ViewAnimations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseAuthFragment<FragmentRegisterBinding>() {
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authButton.setOnClickListener {
            ViewAnimations.blackout(false, binding.cardAuth)
            binding.apply {
                fioEditText.isFocusable = false
                postEditText.isFocusable = false
                organizationEditText.isFocusable = false
                statusPopupButton.isClickable = false
                emailEditText.isFocusable = false
                passwordEditText.isFocusable = false
                authButton.isFocusable = false
                loadingview.visibility = View.VISIBLE
            }
            viewModel.register(AuthRequestBody(
                binding.fioEditText.text.toString(),
                binding.postEditText.text.toString(),
                binding.organizationEditText.toString(),
//                binding.statusPopupButton.text.toString(),
                "status",
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ))
        }
        viewModel.userId.observe(viewLifecycleOwner) {
            ViewAnimations.blackout(true, binding.cardAuth)
            binding.apply {
                fioEditText.isFocusableInTouchMode = true
                postEditText.isFocusableInTouchMode = true
                organizationEditText.isFocusableInTouchMode = true
                statusPopupButton.isClickable = true
                emailEditText.isFocusableInTouchMode = true
                passwordEditText.isFocusableInTouchMode = true
                authButton.isClickable = true
                loadingview.visibility = View.GONE
            }
            handleResult(it)
        }
    }

    private fun handleResult(result: com.turtleteam.myapp.data.wrapper.Result<UserId>) {
        when (result) {
            is Result.Success -> {
                context?.let { UserPreferences(it).getUserId(result.value.token) }
                Toast.makeText(context, result.value.token, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }
            is Result.ConnectionError -> {
                Toast.makeText(context, "Нет удалось подключиться к сети", Toast.LENGTH_LONG).show()
            }
            is Result.Loading -> {
                binding.loadingview.visibility = View.VISIBLE
            }
            is Result.Error,
            is Result.NotFoundError,
            -> {
                Toast.makeText(context, "Не удалось зарегистрироваться", Toast.LENGTH_LONG).show()
            }
            
    private var selectStatus = "СТАТУС"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)

        }

        binding.statusPopupButton.setOnClickListener {
            showPopup(binding.statusPopupButton)
        }
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(view.context, view)
        popup.inflate(R.menu.status_menu)
        popup.setOnMenuItemClickListener {
            selectStatus = it.toString()
            binding.statusPopupButton.text = selectStatus
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
}