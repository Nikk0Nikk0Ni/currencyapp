package com.niko.currencyappp.data.network

import com.niko.currencyappp.domain.models.ValuteResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Main_Api {
    @GET("daily_json.js")
    suspend fun getList(): ValuteResponse
}

val retrofit = Retrofit.Builder().baseUrl("https://www.cbr-xml-daily.ru")
    .addConverterFactory(GsonConverterFactory.create()).build()
val request = retrofit.create(Main_Api::class.java)