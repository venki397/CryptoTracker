package com.venki.cryptotracker.presentation.models

sealed interface CoinListActions {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListActions
}