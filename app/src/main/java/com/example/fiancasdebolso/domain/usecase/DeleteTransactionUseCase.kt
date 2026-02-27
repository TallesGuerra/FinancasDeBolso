package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.data.repository.TransactionRepository

class DeleteTransactionUseCase(private val repository: TransactionRepository) {
    suspend operator fun invoke(transaction: TransactionEntity) {
        repository.delete(transaction)
    }
}