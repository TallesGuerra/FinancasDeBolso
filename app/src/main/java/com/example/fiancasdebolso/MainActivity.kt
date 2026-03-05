package com.example.fiancasdebolso


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fiancasdebolso.ui.AppNavigation
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiancasDeBolsoTheme {
                val app = application as FinancasApp
                AppNavigation(repository = app.repository)
                }
            }
        }
    }




