package com.example.lotoeasy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.ui.theme.LotoOrange

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Bem-vindo ao Loto - Easy",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111)
        )
        Text(
            text = "Acompanhe seus sorteios e resultados",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            DashboardMetricCard(
                title = "Sorteios Ganhos",
                value = "3",
                icon = Icons.Default.Leaderboard,
                brush = Brush.linearGradient(listOf(LotoOrange, Color(0xFFFA7E4B))),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            DashboardMetricCard(
                title = "Total de Talões",
                value = "27",
                icon = Icons.Default.CardMembership,
                brush = Brush.linearGradient(listOf(Color(0xFFFF9100), Color(0xFFFFAA33))),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DashboardMetricCard(
                title = "Próximo Sorteio",
                value = "09/05/2026",
                icon = Icons.Default.DateRange,
                brush = Brush.linearGradient(listOf(Color(0xFFE53935), Color(0xFFFF5252))),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            DashboardMetricCard(
                title = "Melhor Prêmio",
                value = "R$ 1.250,00",
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                brush = Brush.linearGradient(listOf(Color(0xFFC62828), Color(0xFFE53935))),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Últimos Resultados",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                ResultRow(concurso = "Sorteio 001", data = "07/05/2026", acertos = "7", ganhou = false)
                HorizontalDivider(color = Color(0xFFEEEEEE), modifier = Modifier.padding(vertical = 8.dp))
                ResultRow(concurso = "Sorteio 002", data = "06/05/2026", acertos = "15", ganhou = true)
                HorizontalDivider(color = Color(0xFFEEEEEE), modifier = Modifier.padding(vertical = 8.dp))
                ResultRow(concurso = "Sorteio 003", data = "05/05/2026", acertos = "8", ganhou = false)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Próximos Sorteios",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                NextDrawRow(concurso = "Concurso 2850", data = "09/05/2026", valor = "R$ 3.500.000,00")
            }
        }
    }
}

@Composable
fun DashboardMetricCard(title: String, value: String, icon: ImageVector, brush: Brush, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
            Column {
                Text(text = title, color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                Text(text = value, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 2.dp))
            }
        }
    }
}

@Composable
fun ResultRow(concurso: String, data: String, acertos: String, ganhou: Boolean) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = concurso, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xFF333333))
            Text(text = data, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = acertos, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if (ganhou) Color(0xFF2E7D32) else Color(0xFFC62828))
            Text(text = if (ganhou) "Ganhou!" else "Perdeu", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = if (ganhou) Color(0xFF2E7D32) else Color.Gray)
        }
    }
}

@Composable
fun NextDrawRow(concurso: String, data: String, valor: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = concurso, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xFF333333))
            Text(text = data, fontSize = 12.sp, color = Color.Gray)
        }
        Text(text = valor, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = LotoOrange)
    }
}