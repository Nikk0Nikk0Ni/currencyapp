package com.niko.currencyappp.domain.useCase

import androidx.lifecycle.LiveData
import com.niko.currencyappp.domain.models.Valute
import com.niko.currencyappp.domain.repository.ListCurrencyRepository

class GetListUseCase(private val repository: ListCurrencyRepository){
    fun getList(): LiveData<List<Valute>>{
        return repository.getListCurrency()
    }
}