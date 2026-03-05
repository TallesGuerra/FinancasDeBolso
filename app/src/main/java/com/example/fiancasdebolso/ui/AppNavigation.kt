package com.example.fiancasdebolso.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fiancasdebolso.data.repository.TransactionRepository
import com.example.fiancasdebolso.ui.add_transaction.AddTransactionScreen
import com.example.fiancasdebolso.ui.add_transaction.AddTransactionViewModel
import com.example.fiancasdebolso.ui.history.HistoryScreen
import com.example.fiancasdebolso.ui.history.HistoryViewModel
import com.example.fiancasdebolso.ui.home.HomeScreen
import com.example.fiancasdebolso.ui.home.HomeViewModel

@Composable
fun AppNavigation(repository: TransactionRepository) {

    val navController = rememberNavController()
    val factory = ViewModelFactory(repository)

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate("add_transaction") },
                onNavigateToHistory = { navController.navigate("history") }
            )
        }
        composable("add_transaction") {
            val viewModel: AddTransactionViewModel = viewModel(factory = factory)
            AddTransactionScreen(
                viewModel = viewModel,
                onTransactionAdded = { navController.popBackStack() }
            )
        }
        composable("history") {
            val viewModel: HistoryViewModel = viewModel(factory = factory)
            HistoryScreen(viewModel = viewModel)
        }
    }
}
