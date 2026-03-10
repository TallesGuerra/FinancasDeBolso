package com.example.fiancasdebolso.ui.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Formata um Double para Euro: 1.234,56 € */
fun Double.toCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "PT"))
    return formatter.format(this)
}

/** Formata um timestamp Long para data relativa ou dd/MM/aa */
fun Long.toFormattedDate(): String {
    val diff = System.currentTimeMillis() - this
    return when {
        diff < 86_400_000L  -> "Hoje"
        diff < 172_800_000L -> "Ontem"
        else -> SimpleDateFormat("dd/MM/yy", Locale("pt", "BR")).format(Date(this))
    }
}
