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
import com.turtleteam.myapp.ui.fragments.home.setting.SettingFragment


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.homeFragmentContainerView, EventsFragment()).commitNow()
                    true
                }
                R.id.participateFragment -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.homeFragmentContainerView, ParticipateFragment()).commitNow()
                    true
                }
                R.id.settingFragment -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.homeFragmentContainerView, SettingFragment())
                        .commitNow()
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