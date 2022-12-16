package com.turtleteam.myapp.ui.fragments.home.participate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.turtleteam.myapp.databinding.FragmentParticipateBinding


class ParticipateFragment : Fragment() {

    private lateinit var binding: FragmentParticipateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParticipateBinding.inflate(inflater, container, false)
        return binding.root
    }
}