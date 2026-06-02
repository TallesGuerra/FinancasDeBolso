package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.repository.TransactionRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetBalanceUseCaseTest {

    private val repository: TransactionRepository = mockk()
    private lateinit var useCase: GetBalanceUseCase

    @Before
    fun setup() {
        useCase = GetBalanceUseCase(repository)
    }

    @Test
    fun `quando receita maior que despesa, saldo deve ser positivo`() = runTest {
        // DADO
        every { repository.getTotalIncome() } returns flowOf(5000.0)
        every { repository.getTotalExpense() } returns flowOf(1200.0)

        // QUANDO
        val resultado = useCase().first()

        // ENTÃO
        assertEquals(3800.0, resultado, 0.001)
    }

    @Test
    fun `quando despesa maior que receita, saldo deve ser negativo`() = runTest {
        // DADO
        every { repository.getTotalIncome() } returns flowOf(500.0)
        every { repository.getTotalExpense() } returns flowOf(800.0)

        // QUANDO
        val resultado = useCase().first()

        // ENTÃO
        assertEquals(-300.0, resultado, 0.001)
    }

    @Test
    fun `quando sem transacoes, saldo deve ser zero`() = runTest {
        // DADO
        every { repository.getTotalIncome() } returns flowOf(0.0)
        every { repository.getTotalExpense() } returns flowOf(0.0)

        // QUANDO
        val resultado = useCase().first()

        // ENTÃO
        assertEquals(0.0, resultado, 0.001)
    }
}
