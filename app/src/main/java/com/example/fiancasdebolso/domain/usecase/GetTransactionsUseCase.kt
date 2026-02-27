package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.repository.TransactionRepository

class GetTransactionsUseCase(private val repository: TransactionRepository) {
    operator fun invoke() = repository.getAllTransactions()
}