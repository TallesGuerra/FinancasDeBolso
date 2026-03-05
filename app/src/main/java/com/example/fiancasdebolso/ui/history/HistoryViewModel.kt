package com.example.fiancasdebolso.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity

@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {

    val transactions by viewModel.transactions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Histórico",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (transactions.isEmpty()) {
            Text(text = "Nenhuma transação encontrada.")
        } else {
            LazyColumn {
                items(transactions) { transaction ->
                    HistoryItem(transaction = transaction)
                }
            }
        }
    }
}

@Composable
fun HistoryItem(transaction: TransactionEntity) {
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
            Column {
                Text(text = transaction.title)
                Text(
                    text = transaction.type,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(text = "R$ ${transaction.amount}")
        }
    }
}