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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val transactions by viewModel.transactions.collectAsState()
    HomeScreenContent(transactions = transactions)
}

@Composable
fun HomeScreenContent(
    transactions: List<TransactionEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "Finanças de Bolso",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Transações recentes")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(transaction = transaction)
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
            Text(text = "R$ ${transaction.amount}")
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
        ),
        TransactionEntity(
            id = 3,
            title = "Mercado",
            amount = 350.0,
            type = "EXPENSE",
            category = "Alimentação",
            date = System.currentTimeMillis()
        )
    )
    FiancasDeBolsoTheme {
        HomeScreenContent(transactions = sampleTransactions)
    }
}
