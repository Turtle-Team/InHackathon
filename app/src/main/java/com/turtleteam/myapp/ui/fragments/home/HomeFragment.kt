package com.turtleteam.myapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turtleteam.myapp.R
import com.turtleteam.myapp.databinding.FragmentHomeBinding
import com.turtleteam.myapp.ui.fragments.home.events.EventsFragment
import com.turtleteam.myapp.ui.fragments.home.participate.ParticipateFragment


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.eventsf -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.homeFragmentContainerView, EventsFragment()).commitNow()
                    true
                }
                R.id.particf -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.homeFragmentContainerView, ParticipateFragment()).commitNow()
                    true
                }
                else -> {
                    false
                }
            }
        }
        return binding.root
    }
}