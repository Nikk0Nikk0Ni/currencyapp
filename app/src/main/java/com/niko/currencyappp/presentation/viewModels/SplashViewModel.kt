package com.niko.currencyappp.presentation.viewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.currencyappp.data.repository.ListCurrencyRepositoryImpl
import com.niko.currencyappp.domain.models.Valute
import com.niko.currencyappp.domain.useCase.GetListUseCase

class SplashViewModel(private val application: Application) : ViewModel(){
    private var _isNetworkAvailable = MutableLiveData<Boolean>()
    val isNetworkAvailable : LiveData<Boolean>
        get() = _isNetworkAvailable

     fun checkConnection() {
        val connectivityManager = application.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value =  networkInfo != null && networkInfo.isConnected
    }

}