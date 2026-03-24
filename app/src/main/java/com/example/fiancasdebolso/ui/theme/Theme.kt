package com.example.fiancasdebolso.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary        = Emerald500,
    onPrimary      = Color.White,
    background     = BackgroundLight,
    onBackground   = Color(0xFF0F172A),
    surface        = CardLight,
    onSurface      = Color(0xFF0F172A),
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = Color(0xFF475569),
)

private val DarkColorScheme = darkColorScheme(
    primary        = Emerald500,
    onPrimary      = Color.White,
    background     = BackgroundDark,
    onBackground   = Color(0xFFF1F5F9),
    surface        = CardDark,
    onSurface      = Color(0xFFF1F5F9),
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = Color(0xFF94A3B8),
)

@Composable
fun PocketFinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
