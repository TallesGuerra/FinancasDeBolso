package com.example.fiancasdebolso.domain.model

data class Transaction(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val date: Long
)

enum class TransactionType {
    INCOME, EXPENSE
}