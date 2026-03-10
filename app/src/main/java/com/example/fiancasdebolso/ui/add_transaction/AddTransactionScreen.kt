package com.example.fiancasdebolso.ui.add_transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel,
    onTransactionAdded: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("EXPENSE") }
    var titleError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Nova Transação", fontWeight = FontWeight.SemiBold)
                },
                navigationIcon = {
                    IconButton(onClick = onTransactionAdded) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Seletor de tipo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = type == "EXPENSE",
                    onClick = { type = "EXPENSE" },
                    label = { Text("💸  Despesa") },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = type == "INCOME",
                    onClick = { type = "INCOME" },
                    label = { Text("💰  Receita") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Descrição
            OutlinedTextField(
                value = title,
                onValueChange = { title = it; titleError = false },
                label = { Text("Descrição") },
                placeholder = { Text("Ex.: Aluguel, Salário...") },
                isError = titleError,
                supportingText = if (titleError) {
                    { Text("Informe uma descrição") }
                } else null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Valor
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it; amountError = false },
                label = { Text("Valor") },
                placeholder = { Text("0,00") },
                prefix = { Text("€ ", style = MaterialTheme.typography.bodyLarge) },
                isError = amountError,
                supportingText = if (amountError) {
                    { Text("Informe um valor válido") }
                } else null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    titleError = title.isBlank()
                    val parsedAmount = amount.replace(",", ".").toDoubleOrNull()
                    amountError = parsedAmount == null || parsedAmount <= 0.0
                    if (!titleError && !amountError) {
                        viewModel.addTransaction(
                            TransactionEntity(
                                title = title.trim(),
                                amount = parsedAmount!!,
                                type = type,
                                category = "Geral",
                                date = System.currentTimeMillis()
                            )
                        )
                        onTransactionAdded()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Salvar",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
