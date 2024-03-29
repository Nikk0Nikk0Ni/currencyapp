package com.niko.currencyappp.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.currencyappp.R
import com.niko.currencyappp.data.repository.ListCurrencyRepositoryImpl
import com.niko.currencyappp.databinding.FragmentCurrencyBinding
import com.niko.currencyappp.domain.useCase.GetListUseCase
import com.niko.currencyappp.presentation.adapter.ListCurrencyAdapter
import com.niko.currencyappp.presentation.viewModels.CurrencyFragmentVMFactory
import com.niko.currencyappp.presentation.viewModels.CurrencyFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyFragment : Fragment(R.layout.fragment_currency) {
    private var _binding: FragmentCurrencyBinding? = null
    private val binding: FragmentCurrencyBinding
        get() = _binding ?: throw RuntimeException("Fragment Currency == null")
    private val adapter = ListCurrencyAdapter()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CurrencyFragmentVMFactory(requireActivity().application)
        )[CurrencyFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        checkNetworkConnection()
        observeLoadingData()
        return binding.root
    }

    private fun observeLoadingData() {
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.loadLayout.visibility = View.GONE
            }
        }
    }

    private fun checkNetworkConnection() {
        viewModel.isNetworkAvailable.observe(viewLifecycleOwner) {
            if (!it) {
                findNavController().navigate(R.id.action_currencyFragment_to_errorFragment, null,
                    navOptions {
                        anim {
                            enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                            popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                            exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                            popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                        }
                        popUpTo(R.id.main_navigation) {
                            inclusive = true
                        }
                    })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
    }

    private fun initRecView() {
        binding.currencyRecView.adapter = adapter
        viewModel.getList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}