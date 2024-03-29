package com.niko.currencyappp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.niko.currencyappp.domain.models.Valute

class ListCurrencyComporator : DiffUtil.ItemCallback<Valute>() {
    override fun areItemsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem.ID == newItem.ID
    }

    override fun areContentsTheSame(oldItem: Valute, newItem: Valute): Boolean {
        return oldItem == newItem
    }
}