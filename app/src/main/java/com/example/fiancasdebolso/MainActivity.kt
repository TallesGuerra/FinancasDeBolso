package com.example.fiancasdebolso


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme
import com.example.fiancasdebolso.ui.home.HomeScreen
import com.example.fiancasdebolso.ui.home.HomeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiancasDeBolsoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {_ ->
                    HomeScreen()
                }
            }
        }
    }
}

/*FALTA
*Adicionar o ViewModelFactory para tratar o erro do MainActivity
*
* */

