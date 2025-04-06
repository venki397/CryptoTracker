package com.venki.cryptotracker.presentation.models

import androidx.annotation.DrawableRes
import com.venki.cryptotracker.domain.Coin
import com.venki.cryptotracker.presentation.coin_detail.components.DataPoint
import com.venki.cryptotracker.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id : String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val marketCapUsd : DisplayableNumber,
    val priceUsd : DisplayableNumber,
    val changePercent24Hr : DisplayableNumber,
    @DrawableRes val iconRes : Int,
    val coinPriceHistory : List<DataPoint> = emptyList()
)

data class DisplayableNumber(
    val value : Double,
    val formatted : String
)

fun Coin.toCoinUI() : CoinUi{
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hour.toDisplayableNumber(),
        marketCapUsd = markCapUsd.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber() : DisplayableNumber {
    val formater = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = formater.format(this)
    )
}