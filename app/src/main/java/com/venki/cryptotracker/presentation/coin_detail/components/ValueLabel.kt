package com.venki.cryptotracker.presentation.coin_detail.components

import java.text.NumberFormat
import java.util.Locale


data class ValueLabel(
    val value : Float,
    val units : String
){
    fun formated(): String{
        val formater = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val factionDigit = when {
                value > 1000 -> 0
                value in 2f..999f -> 2
                else -> 3
            }

            maximumFractionDigits = factionDigit
            minimumFractionDigits = 0
        }

        return "${formater.format(value)} $units"

    }
}
