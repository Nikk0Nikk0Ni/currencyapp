package com.niko.currencyappp.domain.useCase

import com.niko.currencyappp.domain.repository.ListCurrencyRepository

class UpdateListUseCase(private val repository: ListCurrencyRepository) {
    suspend fun updateList(){
        repository.updateList()
    }
}