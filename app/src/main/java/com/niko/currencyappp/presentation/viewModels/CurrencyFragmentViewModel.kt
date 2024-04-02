package com.niko.currencyappp.presentation.viewModels

import android.app.Application
import android.net.ConnectivityManager
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.currencyappp.data.repository.ListCurrencyRepositoryImpl
import com.niko.currencyappp.domain.models.Valute
import com.niko.currencyappp.domain.useCase.GetListUseCase
import com.niko.currencyappp.domain.useCase.UpdateListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyFragmentViewModel(private val application: Application) : ViewModel() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateListUseCase.updateList()
            _isLoading.postValue(true)
        }
        startTimer()
    }

    private val repository = ListCurrencyRepositoryImpl()
    private val getListUseCase = GetListUseCase(repository)
    private val updateListUseCase = UpdateListUseCase(repository)
    private var _isNetworkAvailable = MutableLiveData<Boolean>()
    val isNetworkAvailable: LiveData<Boolean>
        get() = _isNetworkAvailable
    private var timer: CountDownTimer? = null

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private fun checkConnection() {
        val connectivityManager =
            application.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value = networkInfo != null && networkInfo.isConnected
    }

    fun getList(): LiveData<List<Valute>> {
        return getListUseCase.getList()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(Long.MAX_VALUE, 30000) {
            override fun onTick(p0: Long) {
                checkConnection()
                if (isNetworkAvailable.value == true) {
                    viewModelScope.launch {
                        updateListUseCase.updateList()
                    }
                }
            }

            override fun onFinish() {}
        }
        timer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}