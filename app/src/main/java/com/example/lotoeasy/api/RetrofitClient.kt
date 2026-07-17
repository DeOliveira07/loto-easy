package com.example.lotoeasy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://servicebus2.caixa.gov.br/portaldeloterias/api/"

    val lotomaniaApi: LotomaniaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LotomaniaApi::class.java)
    }
}