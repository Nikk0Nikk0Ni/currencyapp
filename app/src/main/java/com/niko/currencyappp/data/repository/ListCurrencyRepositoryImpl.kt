package com.niko.currencyappp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niko.currencyappp.data.network.request
import com.niko.currencyappp.domain.models.Valute
import com.niko.currencyappp.domain.models.ValuteResponse
import com.niko.currencyappp.domain.repository.ListCurrencyRepository

class ListCurrencyRepositoryImpl : ListCurrencyRepository {
    private var response: ValuteResponse? = null
    private val listLD = MutableLiveData<List<Valute>>()
    private var list = listOf<Valute>()

    override fun getListCurrency(): LiveData<List<Valute>> {
        listLD.postValue(list)
        return listLD
    }

    override suspend fun updateList() {
        response = request.getList()
        response?.let {
            list = it.Valute.values.toList().map { el -> el.copy(Date = it.Date) }
        }
        getListCurrency()
    }
}