package com.turtleteam.myapp.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.turtleteam.myapp.R
import com.turtleteam.myapp.data.preferences.UserPreferences
import com.turtleteam.myapp.databinding.FragmentSplashBinding
import com.turtleteam.myapp.ui.fragments.auth.base.BaseAuthFragment
import com.turtleteam.myapp.ui.fragments.auth.loginfragment.AuthFragment
import com.turtleteam.myapp.ui.fragments.home.HomeFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseAuthFragment<FragmentSplashBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            if (UserPreferences(requireContext()).setUserId() != "0") {
                delay(1000)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                delay(1000)
                findNavController().navigate(R.id.action_splashFragment_to_authFragment)
            }
        }
    }
}