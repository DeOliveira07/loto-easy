package com.example.lotoeasy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.MainViewModel
import com.example.lotoeasy.model.Talao
import com.example.lotoeasy.ui.theme.LotoOrange

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    val taloes = viewModel.taloes
    val lotomaniaData = viewModel.lotomaniaState

    val dezenasSorteadasOficiais = lotomaniaData?.listaDezenas?.mapNotNull { it.toIntOrNull() } ?: emptyList()
    val concursoOficialAtual = lotomaniaData?.numero?.toString() ?: ""

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Histórico de Registros",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        if (taloes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhum talão cadastrado ainda.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            taloes.forEachIndexed { index, talao ->
                var isExpanded by remember { mutableStateOf(index == 0) }

                val jaApurado = talao.concurso == concursoOficialAtual
                val dezenasSorteio = if (jaApurado) dezenasSorteadasOficiais else emptyList()
                val acertosCalculados = if (jaApurado) {
                    talao.numerosApostados.count { it in dezenasSorteio }
                } else {
                    talao.acertos
                }

                HistoryCard(
                    talao = talao,
                    acertosReais = acertosCalculados,
                    jaApurado = jaApurado,
                    dezenasSorteadas = dezenasSorteio,
                    isExpanded = isExpanded,
                    onCardClick = { isExpanded = !isExpanded }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun HistoryCard(
    talao: Talao,
    acertosReais: Int,
    jaApurado: Boolean,
    dezenasSorteadas: List<Int>,
    isExpanded: Boolean,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(LotoOrange.copy(alpha = 0.12f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Leaderboard,
                        contentDescription = null,
                        tint = LotoOrange,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = talao.titulo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF222222)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = " Concurso ${talao.concurso} • ${talao.data}",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (jaApurado) "Acertos " else "Pendente ",
                            fontSize = 12.sp,
                            color = if (jaApurado) Color.Gray else LotoOrange
                        )
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Expandir",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = if (jaApurado) "$acertosReais" else "?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (jaApurado && (acertosReais >= 15 || acertosReais == 0)) Color(0xFF16A34A) else LotoOrange,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    HorizontalDivider(color = Color(0xFFEEEEEE))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Números apostados (${talao.numerosApostados.size}):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF555555),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OptIn(ExperimentalLayoutApi::class)
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        talao.numerosApostados.sorted().forEach { numero ->
                            val foiSorteado = jaApurado && numero in dezenasSorteadas
                            val corBola = if (foiSorteado) Color(0xFF16A34A) else Color(0xFF1D52D2)

                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(corBola, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = numero.toString(),
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}