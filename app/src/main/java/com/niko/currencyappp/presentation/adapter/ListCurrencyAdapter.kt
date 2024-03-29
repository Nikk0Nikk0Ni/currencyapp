package com.niko.currencyappp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.currencyappp.R
import com.niko.currencyappp.domain.models.Valute

class ListCurrencyAdapter : ListAdapter<Valute,ListCurrencyHolder>(ListCurrencyComporator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCurrencyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent,false)
        return ListCurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: ListCurrencyHolder, position: Int) {
        holder.bind(getItem(position))
    }
}