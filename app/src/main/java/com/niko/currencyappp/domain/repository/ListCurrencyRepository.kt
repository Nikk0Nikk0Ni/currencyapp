package com.niko.currencyappp.domain.repository

import androidx.lifecycle.LiveData
import com.niko.currencyappp.domain.models.Valute

interface ListCurrencyRepository {
    fun getListCurrency() : LiveData<List<Valute>>
    suspend fun updateList()
}