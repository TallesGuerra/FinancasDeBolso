package com.example.fiancasdebolso.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainDispatcherRule (
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher())
    //TestWatcher é uma classe do JUnit que observa o ciclo de vida de cada teste.
    // Ela te dá dois momentos pra executar código: starting e  finished
    : TestWatcher(){

    override  fun starting(description: Description?){
        Dispatchers.setMain(dispatcher) // substitui o Main real pelo de teste
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain() // restaura o Main original
    }
}

/*UnconfinedTestDispatcher » É o dispatcher de teste que executa coroutines imediatamente e de
forma síncrona. Quando o stateIn do ViewModel tenta coletar o Flow, ele executa na hora sem
precisar de controle manual.*/