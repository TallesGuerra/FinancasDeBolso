package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTotalExpenseUseCase(private val repository: TransactionRepository) {
    operator fun invoke(): Flow<Double> = repository.getTotalExpense()
}
