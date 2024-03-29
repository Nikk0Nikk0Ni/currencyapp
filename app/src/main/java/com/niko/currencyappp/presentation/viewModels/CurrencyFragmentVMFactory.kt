package com.niko.currencyappp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrencyFragmentVMFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CurrencyFragmentViewModel::class.java))
            return CurrencyFragmentViewModel(application) as T
        else
            throw RuntimeException("${modelClass.simpleName} != CurrencyFragmentViewModel")
    }
}