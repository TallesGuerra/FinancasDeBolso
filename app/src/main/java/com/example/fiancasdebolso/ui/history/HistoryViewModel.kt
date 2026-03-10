package com.example.fiancasdebolso.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiancasdebolso.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    val transactions = getTransactionsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}

