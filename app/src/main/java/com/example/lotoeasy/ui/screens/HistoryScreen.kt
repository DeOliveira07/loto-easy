package com.example.lotoeasy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.ui.theme.LotoOrange

data class TalaoHistorico(
    val id: String,
    val titulo: String,
    val data: String,
    val acertos: Int,
    val numerosApostados: List<Int>
)

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    val historicoMock = remember {
        listOf(
            TalaoHistorico(
                id = "001",
                titulo = "Sorteio 001 - Maio 2026",
                data = "07/05/2026",
                acertos = 7,
                numerosApostados = listOf(
                    1, 3, 5, 7, 9, 11, 13, 15, 17, 19,
                    21, 23, 25, 27, 29, 31, 33, 35, 37,
                    39, 41, 43, 45, 47, 49, 51, 53, 55,
                    57, 59, 61, 63, 65, 67, 69, 71, 73,
                    75, 77, 79, 81, 83, 85, 87, 89, 91,
                    93, 95, 97, 99
                )
            ),
            TalaoHistorico(
                id = "002",
                titulo = "Sorteio 002 - Maio 2026",
                data = "06/05/2026",
                acertos = 5,
                numerosApostados = listOf(2, 4, 6, 8, 10, 20, 30, 40, 50)
            ),
            TalaoHistorico(
                id = "003",
                titulo = "Sorteio 003 - Maio 2026",
                data = "05/05/2026",
                acertos = 8,
                numerosApostados = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
            )
        )
    }

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

        historicoMock.forEachIndexed { index, talao ->
            var isExpanded by remember { mutableStateOf(index == 0) }

            HistoryCard(
                talao = talao,
                isExpanded = isExpanded,
                onCardClick = { isExpanded = !isExpanded }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun HistoryCard(
    talao: TalaoHistorico,
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
                            text = " ${talao.data}",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Acertos ",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Expandir",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = "${talao.acertos}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = LotoOrange,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    HorizontalDivider(color = Color(0xFFEEEEEE))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Números apostados:",
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
                        talao.numerosApostados.forEach { numero ->
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(Color(0xFF1D52D2), shape = CircleShape),
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