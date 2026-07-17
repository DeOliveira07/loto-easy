package com.example.lotoeasy.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LotomaniaApi {

    @GET("lotomania")
    fun getUltimoConcurso(): Call<LotomaniaResponse>

    @GET("lotomania/{concurso}")
    fun getConcursoEspecifico(@Path("concurso") concurso: Int): Call<LotomaniaResponse>
}