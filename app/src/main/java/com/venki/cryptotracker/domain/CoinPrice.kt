package com.venki.cryptotracker.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd : Double,
    val dateTime: ZonedDateTime
)
