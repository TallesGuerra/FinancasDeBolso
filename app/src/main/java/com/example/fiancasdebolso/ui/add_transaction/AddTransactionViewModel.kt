package com.example.fiancasdebolso.ui.add_transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity

@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel,
    onTransactionAdded: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("EXPENSE") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nova Transação",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Valor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            FilterChip(
                selected = type == "EXPENSE",
                onClick = { type = "EXPENSE" },
                label = { Text("Despesa") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = type == "INCOME",
                onClick = { type = "INCOME" },
                label = { Text("Receita") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && amount.isNotBlank()) {
                    viewModel.addTransaction(
                        TransactionEntity(
                            title = title,
                            amount = amount.toDouble(),
                            type = type,
                            category = "Geral",
                            date = System.currentTimeMillis()
                        )
                    )
                    onTransactionAdded()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}