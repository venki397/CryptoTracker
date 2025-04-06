package com.venki.cryptotracker.data.dto


import com.venki.cryptotracker.domain.Coin
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)

@Serializable
data class CoinResponseDto(
    val data: List<CoinDto>
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        markCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hour = changePercent24Hr
    )
}