package com.niko.currencyappp.presentation.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.currencyappp.R
import com.niko.currencyappp.data.repository.ListCurrencyRepositoryImpl
import com.niko.currencyappp.databinding.FragmentSplashBinding
import com.niko.currencyappp.presentation.viewModels.SplashViewModel
import com.niko.currencyappp.presentation.viewModels.SplashViewModelFactory

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(
            this,
            SplashViewModelFactory(requireActivity().application)
        )[SplashViewModel::class.java]
    }
    private val navOptions = navOptions {
        anim {
            enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
            popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
            exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
            popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
        }
        popUpTo(R.id.main_navigation) {
            inclusive = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        checkNetwork()
        return binding.root
    }

    private fun checkNetwork() {
        viewModel.isNetworkAvailable.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_splashFragment_to_currencyFragment,null,navOptions)
            } else
                findNavController().navigate(R.id.action_splashFragment_to_errorFragment, null, navOptions)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rotateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anim)
        binding.imageView.startAnimation(rotateAnimation)
        Handler().postDelayed({ viewModel.checkConnection() }, 3000)
    }

}