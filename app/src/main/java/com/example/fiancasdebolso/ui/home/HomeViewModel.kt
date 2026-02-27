package com.example.fiancasdebolso.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.domain.usecase.DeleteTransactionUseCase
import com.example.fiancasdebolso.domain.usecase.GetBalanceUseCase
import com.example.fiancasdebolso.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
) : ViewModel() {
    val transactions = getTransactionsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val totalIncome = getBalanceUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0.0)

    fun deleteTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            deleteTransactionUseCase(transaction)
        }
    }
}

