package com.niko.currencyappp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.currencyappp.R
import com.niko.currencyappp.databinding.CurrencyItemBinding
import com.niko.currencyappp.domain.models.Valute

class ListCurrencyHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = CurrencyItemBinding.bind(view)
    fun bind(valute: Valute) {
        binding.apply {
            tvName.text = String.format(
                view.context.getString(R.string.currency_to_rub), valute.Name
            )
            tvValue.text = valute.Value.toString()
            tvLastUpdate.text = String.format(
                view.context.getString(
                    R.string.the_time_of_the_last_update,
                    valute.Date
                )
            )
        }
    }
}