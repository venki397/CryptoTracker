package com.venki.cryptotracker.domain

import java.time.ZonedDateTime

//will contains the list of APIs
interface CoinDataSource {
    suspend fun getCoins() : Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId : String,
        start : ZonedDateTime,
        end : ZonedDateTime): Result<List<CoinPrice>, NetworkError>
}