package com.turtleteam.myapp.ui.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turtleteam.myapp.R
import com.turtleteam.myapp.databinding.FragmentAuthBinding
import com.turtleteam.myapp.databinding.FragmentHomeBinding


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }
    }
}