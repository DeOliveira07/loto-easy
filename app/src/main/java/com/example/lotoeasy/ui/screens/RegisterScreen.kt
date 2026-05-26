package com.example.lotoeasy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotoeasy.ui.theme.BackgroundWhite
import com.example.lotoeasy.ui.theme.LotoOrange

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit = {},
    onBackToLoginClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val backgroundColor = BackgroundWhite
    val primaryColor = LotoOrange

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "LOTO-EASY",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Crie sua conta para começar",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo Nome
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome Completo", color = Color.Gray) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Gray) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha", color = Color.Gray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Senha", color = Color.Gray) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        Button(
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(12.dp),
            enabled = name.isNotEmpty() && email.isNotEmpty() &&
                    password.isNotEmpty() && password == confirmPassword,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(
                text = "Cadastrar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Já tem uma conta?",
                color = Color.Gray,
                fontSize = 14.sp
            )
            TextButton(onClick = onBackToLoginClick) {
                Text(
                    text = "Entre aqui",
                    color = primaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}