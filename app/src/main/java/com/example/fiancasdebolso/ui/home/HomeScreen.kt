package com.example.fiancasdebolso.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val transactions by viewModel.transactions.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()

    HomeScreenContent(
        transactions = transactions,
        totalIncome = totalIncome,
        onNavigateToAdd = onNavigateToAdd,
        onNavigateToHistory = onNavigateToHistory
    )
}

@Composable
fun HomeScreenContent(
    transactions: List<TransactionEntity>,
    totalIncome: Double,
    onNavigateToAdd: () -> Unit,
    onNavigateToHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Finanças de Bolso",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Saldo atual")
                    Text(
                        "€ $totalIncome",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Transações recentes")
                IconButton(onClick = onNavigateToHistory) {
                    Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Histórico")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (transactions.isEmpty()) {
                Text(text = "Nenhuma transação ainda.")
            } else {
                LazyColumn {
                    items(transactions) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: TransactionEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = transaction.title)
            Text(
                text = "€ ${transaction.amount}",
                color = if (transaction.type == "INCOME") Color.Green else Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
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
    FiancasDeBolsoTheme {
        HomeScreenContent(
            transactions = sampleTransactions,
            totalIncome = 3800.0,
            onNavigateToAdd = {},
            onNavigateToHistory = {}
        )
    }
}
