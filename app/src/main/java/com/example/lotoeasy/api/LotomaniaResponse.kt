package com.example.lotoeasy.api

import com.google.gson.annotations.SerializedName

data class LotomaniaResponse(
    @SerializedName("numero")
    val numero: Int? = null,

    @SerializedName("dataApuracao")
    val dataApuracao: String? = null,

    @SerializedName("listaDezenas")
    val listaDezenas: List<String>? = null,

    @SerializedName("valorEstimadoProximoConcurso")
    val valorEstimadoProximoConcurso: Double? = null,

    @SerializedName("dataProximoConcurso")
    val dataProximoConcurso: String? = null,

    @SerializedName("numeroConcursoProximo")
    val numeroConcursoProximo: Int? = null
)