package com.niko.currencyappp.presentation.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplashViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)){
            return SplashViewModel(application) as T
        }
        else
            throw RuntimeException("${modelClass.simpleName} != SplashViewModel")
    }
}