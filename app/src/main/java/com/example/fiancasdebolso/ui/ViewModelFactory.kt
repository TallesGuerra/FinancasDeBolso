package com.example.fiancasdebolso.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fiancasdebolso.data.repository.TransactionRepository
import com.example.fiancasdebolso.domain.usecase.AddTransactionUseCase
import com.example.fiancasdebolso.domain.usecase.DeleteTransactionUseCase
import com.example.fiancasdebolso.domain.usecase.GetBalanceUseCase
import com.example.fiancasdebolso.domain.usecase.GetTransactionsUseCase
import com.example.fiancasdebolso.ui.add_transaction.AddTransactionViewModel
import com.example.fiancasdebolso.ui.history.HistoryViewModel
import com.example.fiancasdebolso.ui.home.HomeViewModel
import kotlin.jvm.java

class ViewModelFactory(
    private val repository: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                getTransactionsUseCase = GetTransactionsUseCase(repository),
                deleteTransactionUseCase = DeleteTransactionUseCase(repository),
                getBalanceUseCase = GetBalanceUseCase(repository)
            )
            modelClass.isAssignableFrom(AddTransactionViewModel::class.java) -> AddTransactionViewModel(
                addTransactionUseCase = AddTransactionUseCase(repository)
            )
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(
                getTransactionsUseCase = GetTransactionsUseCase(repository)
            )
            else -> throw IllegalArgumentException("ViewModel não encontrado")
        } as T
    }
}