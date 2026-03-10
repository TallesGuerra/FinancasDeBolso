package com.example.fiancasdebolso.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.ui.theme.BalanceCardBg
import com.example.fiancasdebolso.ui.theme.ExpenseRed
import com.example.fiancasdebolso.ui.theme.ExpenseRedSurface
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme
import com.example.fiancasdebolso.ui.theme.IncomeGreen
import com.example.fiancasdebolso.ui.theme.IncomeGreenSurface
import com.example.fiancasdebolso.ui.utils.toBRL
import com.example.fiancasdebolso.ui.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val transactions by viewModel.transactions.collectAsState()
    val balance by viewModel.balance.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()

    HomeScreenContent(
        transactions = transactions,
        balance = balance,
        totalIncome = totalIncome,
        totalExpense = totalExpense,
        onNavigateToAdd = onNavigateToAdd,
        onNavigateToHistory = onNavigateToHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    transactions: List<TransactionEntity>,
    balance: Double,
    totalIncome: Double,
    totalExpense: Double,
    onNavigateToAdd: () -> Unit,
    onNavigateToHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
         topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Finanças no Bolso",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    TextButton(onClick = onNavigateToHistory) {
                        Text("Ver tudo")
                        Spacer(Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Histórico completo",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nova transação",
                    tint = Color.White
                )
            }
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(4.dp))

            BalanceCard(
                balance = balance,
                totalIncome = totalIncome,
                totalExpense = totalExpense
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Transações recentes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            if (transactions.isEmpty()) {
                EmptyTransactionsState()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(transactions.take(10)) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

@Composable
fun BalanceCard(balance: Double, totalIncome: Double, totalExpense: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BalanceCardBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Saldo atual",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.75f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = balance.toBRL(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = if (balance >= 0) Color.White else Color(0xFFFF8A80)
            )

            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("▲", color = Color(0xFF80E57A), style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Receitas",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                    Text(
                        text = totalIncome.toBRL(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF80E57A)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("▼", color = Color(0xFFFF8A80), style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Despesas",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                    Text(
                        text = totalExpense.toBRL(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFF8A80)
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: TransactionEntity) {
    val isIncome = transaction.type == "INCOME"
    val amountColor = if (isIncome) IncomeGreen else ExpenseRed
    val iconBg = if (isIncome) IncomeGreenSurface else ExpenseRedSurface
    val iconSymbol = if (isIncome) "↑" else "↓"
    val prefix = if (isIncome) "+" else "-"

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = iconSymbol,
                    color = amountColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${transaction.category} · ${transaction.date.toFormattedDate()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "$prefix${transaction.amount.toBRL()}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = amountColor
            )
        }
    }
}

@Composable
fun EmptyTransactionsState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("💸", style = MaterialTheme.typography.displayMedium)
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Nenhuma transação ainda",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Toque em + para adicionar",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val sampleTransactions = listOf(
        TransactionEntity(
            id = 1, title = "Salário", amount = 5000.0,
            type = "INCOME", category = "Trabalho",
            date = System.currentTimeMillis()
        ),
        TransactionEntity(
            id = 2, title = "Aluguel", amount = 1200.0,
            type = "EXPENSE", category = "Moradia",
            date = System.currentTimeMillis() - 86_400_000L
        )
    )
    FiancasDeBolsoTheme {
        HomeScreenContent(
            transactions = sampleTransactions,
            balance = 3800.0,
            totalIncome = 5000.0,
            totalExpense = 1200.0,
            onNavigateToAdd = {},
            onNavigateToHistory = {}
        )
    }
}
