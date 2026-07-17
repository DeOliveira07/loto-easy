package com.example.lotoeasy.db.fb

import com.example.lotoeasy.model.Talao

data class FBTalao(
    var id: String = "",
    var titulo: String = "",
    var concurso: String = "",
    var data: String = "",
    var numerosApostados: List<Long> = emptyList(),
    var acertos: Int = 0
) {
    fun toTalao(): Talao {
        return Talao(
            id = id,
            titulo = titulo,
            concurso = concurso,
            data = data,
            numerosApostados = numerosApostados.map { it.toInt() },
            acertos = acertos
        )
    }
}

fun Talao.toFBTalao(): FBTalao {
    return FBTalao(
        id = id,
        titulo = titulo,
        concurso = concurso,
        data = data,
        numerosApostados = numerosApostados.map { it.toLong() },
        acertos = acertos
    )
}