package com.example.fiancasdebolso.domain.usecase

import com.example.fiancasdebolso.data.local.entity.TransactionEntity
import com.example.fiancasdebolso.data.repository.TransactionRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs

import kotlinx.coroutines.test.runTest
import kotlin.test.assertFailsWith
import org.junit.Before
import org.junit.Test

class AddTransactionUseCaseTest {
    private val repository: TransactionRepository = mockk()
    private lateinit var useCase: AddTransactionUseCase

    @Before
    fun setup(){
        useCase = AddTransactionUseCase (repository)
    }

    @Test
    fun `deve chamar insert no repository com a transacao correta`() = runTest {
        // DADO
        val transaction = TransactionEntity(
            id = 1,
            title = "Salário",
            amount = 5000.0,
            type = "INCOME",
            category = "Trabalho",
            date = 123456789L
        )
        coEvery { repository.insert(transaction) } just runs

        // QUANDO
        useCase(transaction)

        // ENTÃO
        coVerify { repository.insert(transaction) }
    }

    @Test
    fun `quando insert falha, deve propagar a excecao`() = runTest {
        val transaction = TransactionEntity(
            id = 1, title = "Salário", amount = 5000.0,
            type = "INCOME", category = "Trabalho", date = 123456789L
        )
        coEvery { repository.insert(transaction) } throws RuntimeException("Erro no banco")

        assertFailsWith<RuntimeException> {
            useCase(transaction)   // ← funciona porque assertFailsWith aceita suspend
        }
        // coVerify aqui é redundante — se a exceção foi lançada, insert foi chamado
    }

    @Test
    fun `deve chamar insert no repository com uma despesa`() = runTest {
        val transaction = TransactionEntity(1, "Lanche", 15.5, "EXPENSE", "Alimentação", 123L)
        coEvery { repository.insert(any()) } just runs

        useCase(transaction)

        coVerify { repository.insert(match { it.type == "EXPENSE" }) }
        //↑ verifica o que realmente importa: o tipo
    }

}