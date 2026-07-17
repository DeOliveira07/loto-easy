package com.example.lotoeasy.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.MainViewModel
import com.example.lotoeasy.ui.theme.BackgroundWhite
import com.example.lotoeasy.ui.theme.LotoOrange

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RaffleRegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var selectedNumbers by remember { mutableStateOf(setOf<Int>()) }
    var raffleName by remember { mutableStateOf("") }
    var concurso by remember { mutableStateOf("") }
    var drawDate by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val numbers = (1..100).toList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Cabeçalho
        Text(
            text = "Cadastro de Talão",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Text(
            text = "Preencha as informações do seu talão e selecione seus números da sorte.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = raffleName,
                    onValueChange = { raffleName = it },
                    label = { Text("Nome do Talão") },
                    placeholder = { Text("Ex: Talão Lucky 001") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LotoOrange,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = LotoOrange,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = LotoOrange
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = concurso,
                    onValueChange = { concurso = it },
                    label = { Text("Número do Concurso") },
                    placeholder = { Text("Ex: 2850") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LotoOrange,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = LotoOrange,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = LotoOrange
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = drawDate,
                    onValueChange = { drawDate = it },
                    label = { Text("Data do Resultado") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LotoOrange,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = LotoOrange,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = LotoOrange
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Números selecionados: ")
                    withStyle(style = SpanStyle(color = LotoOrange, fontWeight = FontWeight.Bold)) {
                        append("${selectedNumbers.size}")
                    }
                },
                fontSize = 14.sp,
                color = Color.Gray
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = {
                        selectedNumbers = emptySet()
                        raffleName = ""
                        concurso = ""
                        drawDate = ""
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFFF3F4F6)
                    )
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Limpar", tint = Color.Gray)
                }

                // Botão de Salvar integrado ao ViewModel e Firestore
                Button(
                    onClick = {
                        viewModel.cadastrarTalao(
                            titulo = raffleName,
                            concurso = concurso,
                            data = drawDate,
                            numeros = selectedNumbers.toList()
                        ) { success, error ->
                            if (success) {
                                Toast.makeText(context, "Talão salvo com sucesso!", Toast.LENGTH_SHORT).show()
                                // Limpa a tela
                                selectedNumbers = emptySet()
                                raffleName = ""
                                concurso = ""
                                drawDate = ""
                            } else {
                                Toast.makeText(context, "Erro ao salvar: $error", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LotoOrange),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Salvar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 10
        ) {
            numbers.forEach { num ->
                val isSelected = selectedNumbers.contains(num)
                NumberButton(
                    number = num,
                    isSelected = isSelected,
                    onClick = {
                        selectedNumbers = if (isSelected) {
                            selectedNumbers - num
                        } else {
                            selectedNumbers + num
                        }
                    }
                )
            }
        }

        // Resumo dos números selecionados
        if (selectedNumbers.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = LotoOrange.copy(alpha = 0.05f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Números selecionados:",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        selectedNumbers.sorted().forEach { num ->
                            Surface(
                                color = LotoOrange,
                                shape = RoundedCornerShape(50),
                            ) {
                                Text(
                                    text = num.toString(),
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun NumberButton(
    number: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(
                color = if (isSelected) LotoOrange else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) LotoOrange else Color(0xFFE5E7EB),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = if (isSelected) Color.White else Color(0xFF374151),
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(8.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 2.dp, end = 2.dp)
            )
        }
    }
}