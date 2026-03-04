package com.example.fiancasdebolso

import android.app.Application

import com.example.fiancasdebolso.data.AppDatabase
import com.example.fiancasdebolso.data.repository.TransactionRepository

class FinancasApp : Application() {

    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { TransactionRepository(database.transactionDao()) }
}