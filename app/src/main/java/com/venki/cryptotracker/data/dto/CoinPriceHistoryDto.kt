package com.venki.cryptotracker.data.dto

import com.venki.cryptotracker.domain.CoinPrice
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId


@Serializable
data class CoinPriceHistory(
    val priceUsd : Double,
    val time: Long,
    val date : String
)

@Serializable
data class CoinPriceHistoryDto(
    val data : List<CoinPriceHistory>
)

fun CoinPriceHistory.toCoinPrice() : CoinPrice {
     return CoinPrice(
         priceUsd = priceUsd,
         dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
     )
}

