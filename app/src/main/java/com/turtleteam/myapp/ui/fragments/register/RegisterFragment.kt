package com.turtleteam.myapp.ui.fragments.register

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import com.turtleteam.myapp.R
import com.turtleteam.myapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private var selectStatus = "СТАТУС"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

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
}
