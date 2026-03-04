package com.example.fiancasdebolso.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
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

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as com.example.fiancasdebolso.FinancasApp
                val repository = application.repository
                return HomeViewModel(
                    getTransactionsUseCase = GetTransactionsUseCase(repository),
                    deleteTransactionUseCase = DeleteTransactionUseCase(repository),
                    getBalanceUseCase = GetBalanceUseCase(repository)
                ) as T
            }
        }
    }
}
