package com.niko.currencyappp.domain.models

data class ValuteResponse(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String,Valute>
)
