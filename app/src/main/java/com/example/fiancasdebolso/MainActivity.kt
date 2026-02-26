package com.example.fiancasdebolso

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fiancasdebolso.ui.theme.FiancasDeBolsoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiancasDeBolsoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeApp()

                }
            }
        }
    }
}

@Composable
fun HomeApp (){
    Text(text = "Home")

}

@Preview(showBackground = true)
@Composable
fun HomeAppPreview() {
    FiancasDeBolsoTheme {
        HomeApp()
    }
}