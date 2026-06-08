package com.example.fiancasdebolso.ui.home

import com.example.fiancasdebolso.domain.usecase.DeleteTransactionUseCase
import com.example.fiancasdebolso.domain.usecase.GetBalanceUseCase
import com.example.fiancasdebolso.domain.usecase.GetTotalExpenseUseCase
import com.example.fiancasdebolso.domain.usecase.GetTotalIncomeUseCase
import com.example.fiancasdebolso.domain.usecase.GetTransactionsUseCase
import com.example.fiancasdebolso.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 5 mocks — um pra cada UseCase
    private val getTransactionsUseCase: GetTransactionsUseCase = mockk()
    private val deleteTransactionUseCase: DeleteTransactionUseCase = mockk()
    private val getBalanceUseCase: GetBalanceUseCase = mockk()
    private val getTotalIncomeUseCase: GetTotalIncomeUseCase = mockk()
    private val getTotalExpenseUseCase: GetTotalExpenseUseCase = mockk()

    private lateinit var  viewModel: HomeViewModel


    @Before
    fun setup() {
        every { getBalanceUseCase() } returns flowOf(0.0)
        every { getTotalIncomeUseCase() } returns flowOf(0.0)
        every { getTotalExpenseUseCase() } returns flowOf(0.0)
        every { getTransactionsUseCase() } returns flowOf(emptyList())



        viewModel = HomeViewModel(
            // passa os 5 na ordem certa
            getTransactionsUseCase = getTransactionsUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            getBalanceUseCase = getBalanceUseCase,
            getTotalIncomeUseCase = getTotalIncomeUseCase,
            getTotalExpenseUseCase = getTotalExpenseUseCase
        )

    }

    @Test
    fun `saldo deve refletir o retorno do use case`() = runTest {
        // DADO
        every { getBalanceUseCase() } returns flowOf(3800.0)

        viewModel = HomeViewModel(
            getTransactionsUseCase = getTransactionsUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            getBalanceUseCase = getBalanceUseCase,
            getTotalIncomeUseCase = getTotalIncomeUseCase,
            getTotalExpenseUseCase = getTotalExpenseUseCase
        )

        // ativa o upstream do StateFlow (necessário com WhileSubscribed)
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.balance.collect {} }

        // QUANDO
        val balance = viewModel.balance.value

        // ENTÃO
        assertEquals(3800.0, balance, 0.001)
    }

    @Test
    fun `saldo deve ser zero quando nao ha transacoes`() = runTest {
        every { getBalanceUseCase() } returns flowOf(0.0)

        viewModel = HomeViewModel(
            getTransactionsUseCase = getTransactionsUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            getBalanceUseCase = getBalanceUseCase,
            getTotalIncomeUseCase = getTotalIncomeUseCase,
            getTotalExpenseUseCase = getTotalExpenseUseCase
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.balance.collect {} }

        // QUANDO
        val balance = viewModel.balance.value

        // ENTÃO
        assertEquals(0.0, balance, 0.001)
    }

    @Test
    fun `total de receitas deve refletir o retorno do use case`() = runTest {
        // DADO
        every { getTotalIncomeUseCase() } returns flowOf(5000.0)

        viewModel = HomeViewModel(
            getTransactionsUseCase = getTransactionsUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            getBalanceUseCase = getBalanceUseCase,
            getTotalIncomeUseCase = getTotalIncomeUseCase,
            getTotalExpenseUseCase = getTotalExpenseUseCase
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.totalIncome.collect {} }

        // QUANDO
        val totalIncome = viewModel.totalIncome.value

        // ENTÃO
        assertEquals(5000.0, totalIncome, 0.001)
    }

    @Test
    fun `total de despesas deve refletir o retorno do use case`() = runTest {
        // DADO
        every { getTotalExpenseUseCase() } returns flowOf(1200.0)

        viewModel = HomeViewModel(
            getTransactionsUseCase = getTransactionsUseCase,
            deleteTransactionUseCase = deleteTransactionUseCase,
            getBalanceUseCase = getBalanceUseCase,
            getTotalIncomeUseCase = getTotalIncomeUseCase,
            getTotalExpenseUseCase = getTotalExpenseUseCase
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.totalExpense.collect {} }

        // QUANDO
        val totalExpense = viewModel.totalExpense.value

        // ENTÃO
        assertEquals(1200.0, totalExpense, 0.001)
    }
}


//1. every { oUseCaseDoQueVouTestar() } returns flowOf(valorEsperado)
//2. viewModel = HomeViewModel(...) — sempre recria
//3. val x = viewModel.oStateFlowCorrespondente.value
//4. assertEquals(valorEsperado, x, 0.001)


//@Before → mocks zerados + ViewModel criado
//@Test   → sobrescreve mock específico → recria
//ViewModel → lê .value → assertEquals