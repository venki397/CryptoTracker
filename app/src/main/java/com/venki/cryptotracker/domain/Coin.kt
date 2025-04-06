package com.venki.cryptotracker.domain

data class Coin(
    val id: String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val markCapUsd : Double,
    val priceUsd : Double,
    val changePercent24Hour: Double
)
