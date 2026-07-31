package com.example.lotoeasy.model

data class Talao(
    var id: String = "",
    val titulo: String = "",
    val concurso: String = "",
    val data: String = "",
    val numerosApostados: List<Int> = emptyList(),
    val acertos: Int = 0
)