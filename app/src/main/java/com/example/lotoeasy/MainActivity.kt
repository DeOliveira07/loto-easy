package com.example.lotoeasy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lotoeasy.db.fb.FBDatabase
import com.example.lotoeasy.ui.screens.DashboardScreen
import com.example.lotoeasy.ui.screens.HistoryScreen
import com.example.lotoeasy.ui.screens.LoginScreen
import com.example.lotoeasy.ui.screens.NextDrawScreen
import com.example.lotoeasy.ui.screens.ProfileScreen
import com.example.lotoeasy.ui.screens.RaffleRegistrationScreen
import com.example.lotoeasy.ui.screens.RegisterScreen
import com.example.lotoeasy.ui.theme.LotoOrange
import com.example.lotoeasy.ui.theme.LotoeasyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LotoeasyTheme {
                val fbDB = remember { FBDatabase() }
                val viewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(fbDB)
                )

                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val context = LocalContext.current

                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.destination?.route

                val showDrawer = currentRoute != "login" && currentRoute != "register" && currentRoute != null

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = showDrawer,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = if (viewModel.user != null) "Olá, ${viewModel.user?.name}" else "LOTO-EASY",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = LotoOrange
                            )

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.Home, null) },
                                label = { Text("Início") },
                                selected = currentRoute == "dashboard",
                                onClick = {
                                    navController.navigate("dashboard") { launchSingleTop = true }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = LotoOrange,
                                    selectedTextColor = LotoOrange,
                                    selectedContainerColor = LotoOrange.copy(alpha = 0.1f)
                                )
                            )

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.DateRange, null) },
                                label = { Text("Próximos Sorteios") },
                                selected = currentRoute == "next_draws",
                                onClick = {
                                    navController.navigate("next_draws") { launchSingleTop = true }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = LotoOrange,
                                    selectedTextColor = LotoOrange,
                                    selectedContainerColor = LotoOrange.copy(alpha = 0.1f)
                                )
                            )

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.History, null) },
                                label = { Text("Histórico de Registros") },
                                selected = currentRoute == "history",
                                onClick = {
                                    navController.navigate("history") { launchSingleTop = true }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = LotoOrange,
                                    selectedTextColor = LotoOrange,
                                    selectedContainerColor = LotoOrange.copy(alpha = 0.1f)
                                )
                            )

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.Add, null) },
                                label = { Text("Cadastrar Talão") },
                                selected = currentRoute == "raffle_registration",
                                onClick = {
                                    navController.navigate("raffle_registration") { launchSingleTop = true }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = LotoOrange,
                                    selectedTextColor = LotoOrange,
                                    selectedContainerColor = LotoOrange.copy(alpha = 0.1f)
                                )
                            )

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.AccountCircle, null) },
                                label = { Text("Meu Perfil") },
                                selected = currentRoute == "profile",
                                onClick = {
                                    navController.navigate("profile") { launchSingleTop = true }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = LotoOrange,
                                    selectedTextColor = LotoOrange,
                                    selectedContainerColor = LotoOrange.copy(alpha = 0.1f)
                                )
                            )

                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                            NavigationDrawerItem(
                                icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, null) },
                                label = { Text("Sair") },
                                selected = false,
                                onClick = {
                                    navController.navigate("login") {
                                        popUpTo("dashboard") { inclusive = true }
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            if (showDrawer) {
                                TopAppBar(
                                    title = {
                                        Text(
                                            when (currentRoute) {
                                                "dashboard" -> "Início"
                                                "next_draws" -> "Sorteios"
                                                "history" -> "Histórico de Registros"
                                                "raffle_registration" -> "Cadastrar Talão"
                                                "profile" -> "Meu Perfil"
                                                else -> "Loto-Easy"
                                            }
                                        )
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch { drawerState.open() }
                                        }) {
                                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                                        }
                                    },
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = LotoOrange.copy(alpha = 0.08f),
                                        titleContentColor = LotoOrange,
                                        navigationIconContentColor = LotoOrange
                                    )
                                )
                            }
                        }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = "login"
                            ) {
                                composable("login") {
                                    LoginScreen(
                                        onLoginClick = {
                                            navController.navigate("dashboard") {
                                                popUpTo("login") { inclusive = true }
                                            }
                                        },
                                        onRegisterClick = { navController.navigate("register") }
                                    )
                                }

                                composable("register") {
                                    RegisterScreen(
                                        onRegisterClick = { name, email, password ->
                                            viewModel.register(name, email, password) { success, error ->
                                                if (success) {
                                                    Toast.makeText(context, "Registro OK!", Toast.LENGTH_LONG).show()
                                                    navController.navigate("dashboard") {
                                                        popUpTo("login") { inclusive = true }
                                                    }
                                                } else {
                                                    Toast.makeText(context, "Registro FALHOU: $error", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                        },
                                        onBackToLoginClick = { navController.popBackStack() }
                                    )
                                }

                                composable("dashboard") { DashboardScreen() }
                                composable("profile") { ProfileScreen() }
                                composable("next_draws") { NextDrawScreen() }
                                composable("history") { HistoryScreen() }
                                composable("raffle_registration") { RaffleRegistrationScreen() }
                            }
                        }
                    }
                }
            }
        }
    }
}