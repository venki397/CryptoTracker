package com.venki.cryptotracker.presentation.coin_list

import com.venki.cryptotracker.domain.NetworkError

sealed interface CoinListEvent{
    data class Error(val error: NetworkError) :CoinListEvent
}