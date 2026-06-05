package com.example.lotoeasy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.ui.theme.BackgroundWhite
import com.example.lotoeasy.ui.theme.LotoOrange

// Modelo de Dados
data class Sorteio(
    val concurso: String,
    val data: String,
    val horario: String,
    val premio: String,
    val status: String
)

val sorteiosMock = listOf(
    Sorteio("2850", "2026-05-10", "20:00", "R$ 3.500.000,00", "Em breve"),
    Sorteio("2851", "2026-05-13", "20:00", "R$ 4.200.000,00", "Pendente"),
    Sorteio("2852", "2026-05-15", "20:00", "R$ 2.800.000,00", "Pendente"),
    Sorteio("2853", "2026-05-17", "20:00", "R$ 5.100.000,00", "Pendente"),
    Sorteio("2854", "2026-05-20", "20:00", "R$ 3.900.000,00", "Pendente")
)

@Composable
fun NextDrawScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Cabeçalho
        Text(
            text = "Próximos Sorteios da Lotomania",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Text(
            text = "Fique por dentro dos próximos sorteios e não perca oportunidades!",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Lista de Cards
        sorteiosMock.forEach { sorteio ->
            SorteioCard(sorteio)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Seção Sobre a Lotomania
        AboutLotomaniaSection()
    }
}

@Composable
fun SorteioCard(sorteio: Sorteio) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone de Calendário com Gradiente
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        brush = Brush.linearGradient(listOf(LotoOrange, Color(0xFFFF8C00))),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Concurso ${sorteio.concurso}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    // Badge de Status
                    Surface(
                        color = when (sorteio.status) {
                            "Hoje" -> Color(0xFFDC2626)
                            "Em breve" -> LotoOrange
                            else -> Color.Gray
                        },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = sorteio.status,
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = " ${sorteio.horario}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Prêmio", fontSize = 11.sp, color = Color.Gray)
                Text(
                    text = sorteio.premio,
                    color = LotoOrange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun AboutLotomaniaSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = LotoOrange)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Sobre a Lotomania",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "Escolha 50 números entre 1 e 100 e concorra a prêmios acertando 20, 19, 18, 17, 16, 15 ou nenhum número!",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoBox(label = "Sorteios", value = "Ter, Qui e Sáb", modifier = Modifier.weight(1f))
                InfoBox(label = "Horário", value = "20:00h", modifier = Modifier.weight(1f))
                InfoBox(label = "Números", value = "20 dezenas", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun InfoBox(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.White.copy(alpha = 0.15f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = label, color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp)
            Text(
                text = value,
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp
            )
        }
    }
}