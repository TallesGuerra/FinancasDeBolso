package com.example.fiancasdebolso.data.repository

import com.example.fiancasdebolso.data.local.dao.TransactionDao
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }

    fun getTransactionsByType(type: String): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsByType(type)
    }

    fun getTotalIncome(): Flow<Double> {
        return transactionDao.getTotalIncome()
    }

    fun getTotalExpense(): Flow<Double> {
        return transactionDao.getTotalExpense()
    }
     suspend fun insert(transaction: TransactionEntity) {
        transactionDao.insert(transaction)
    }

    suspend fun delete(transaction: TransactionEntity) {
        transactionDao.delete(transaction)
    }




}