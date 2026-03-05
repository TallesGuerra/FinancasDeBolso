package com.example.fiancasdebolso.ui.add_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.domain.usecase.AddTransactionUseCase
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    fun addTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            addTransactionUseCase(transaction)
        }
    }
}