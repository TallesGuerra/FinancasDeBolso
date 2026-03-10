package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetBalanceUseCase(private val repository: TransactionRepository) {
    operator fun invoke(): Flow<Double> = combine(
        repository.getTotalIncome(),
        repository.getTotalExpense()
    ) { income, expense -> income - expense }
}