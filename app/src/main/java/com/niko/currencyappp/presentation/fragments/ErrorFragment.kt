package com.niko.currencyappp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.currencyappp.R
import com.niko.currencyappp.databinding.FragmentErrorBinding

class ErrorFragment : Fragment(R.layout.fragment_error) {
    private var _binding : FragmentErrorBinding? = null
    private val binding : FragmentErrorBinding
        get() = _binding ?: throw RuntimeException("Fragment error = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()
    }

    private fun initBtn() {
        binding.btnRetry.setOnClickListener {
            findNavController().navigate(R.id.action_errorFragment_to_splashFragment,
                null,
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                    }
                    popUpTo(R.id.main_navigation){
                        inclusive = true
                    }
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}