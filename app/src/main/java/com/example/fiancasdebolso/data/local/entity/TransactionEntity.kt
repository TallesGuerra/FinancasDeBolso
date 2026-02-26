package com.example.fiancasdebolso.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val type: String, // "INCOME" ou "EXPENSE"
    val category: String,
    val date: Long // salva a data como timestamp
)