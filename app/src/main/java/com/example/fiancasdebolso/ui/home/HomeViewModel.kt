package com.example.fiancasdebolso.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.domain.usecase.DeleteTransactionUseCase
import com.example.fiancasdebolso.domain.usecase.GetBalanceUseCase
import com.example.fiancasdebolso.domain.usecase.GetTotalExpenseUseCase
import com.example.fiancasdebolso.domain.usecase.GetTotalIncomeUseCase
import com.example.fiancasdebolso.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getTotalIncomeUseCase: GetTotalIncomeUseCase,
    private val getTotalExpenseUseCase: GetTotalExpenseUseCase,
) : ViewModel() {

    val transactions = getTransactionsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val balance = getBalanceUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0.0)

    val totalIncome = getTotalIncomeUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0.0)

    val totalExpense = getTotalExpenseUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0.0)

    fun deleteTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            deleteTransactionUseCase(transaction)
        }
    }
}
