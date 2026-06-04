package com.example.lotoeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lotoeasy.ui.screens.LoginScreen
import com.example.lotoeasy.ui.screens.ProfileScreen
import com.example.lotoeasy.ui.screens.RegisterScreen
import com.example.lotoeasy.ui.theme.LotoeasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LotoeasyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    //Rotas do app
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        // Rota da Tela de Login
                        composable("login") {
                            LoginScreen(
                                onLoginClick = {
                                    // Quando clicar em Entrar na demonstração, vai para o Perfil!
                                    navController.navigate("profile")
                                },
                                onRegisterClick = {
                                    navController.navigate("register")
                                }
                            )
                        }

                        // Rota da Tela de Cadastro
                        composable("register") {
                            RegisterScreen(
                                onRegisterClick = {
                                    navController.navigate("login") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onBackToLoginClick = {
                                    navController.navigate("login") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(route = "profile") {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}