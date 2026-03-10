package com.example.fiancasdebolso.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fiancasdebolso.data.local.dao.TransactionDao
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.data.repository.TransactionRepository
import com.example.fiancasdebolso.ui.add_transaction.AddTransactionScreen
import com.example.fiancasdebolso.ui.add_transaction.AddTransactionViewModel
import com.example.fiancasdebolso.ui.history.HistoryScreen
import com.example.fiancasdebolso.ui.history.HistoryViewModel
import com.example.fiancasdebolso.ui.home.HomeScreen
import com.example.fiancasdebolso.ui.home.HomeViewModel
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AppNavigation(repository: TransactionRepository) {

    val navController = rememberNavController()
    val factory = remember { ViewModelFactory(repository) }

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
            HistoryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    val sampleTransactions = listOf(
        TransactionEntity(
            id = 1,
            title = "Salário",
            amount = 5000.0,
            type = "INCOME",
            category = "Trabalho",
            date = System.currentTimeMillis()
        ),
        TransactionEntity(
            id = 2,
            title = "Aluguel",
            amount = 1200.0,
            type = "EXPENSE",
            category = "Moradia",
            date = System.currentTimeMillis()
        )
    )

    val fakeDao = object : TransactionDao {
        override suspend fun insert(transaction: TransactionEntity): Long = 0
        override suspend fun delete(transaction: TransactionEntity): Int = 0
        override fun getAllTransactions(): Flow<List<TransactionEntity>> = MutableStateFlow(sampleTransactions)
        override fun getTransactionsByType(type: String): Flow<List<TransactionEntity>> =
            MutableStateFlow(sampleTransactions.filter { it.type == type })
        override fun getTotalIncome(): Flow<Double> = MutableStateFlow(5000.0)
        override fun getTotalExpense(): Flow<Double> = MutableStateFlow(1200.0)
    }
    val repository = TransactionRepository(fakeDao)
    FiancasDeBolsoTheme {
        AppNavigation(repository = repository)
    }
}
