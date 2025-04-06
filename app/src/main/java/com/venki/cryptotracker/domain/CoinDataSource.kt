package com.venki.cryptotracker.domain

//will contains the list of APIs
interface CoinDataSource {
    suspend fun getCoins() : Result<List<Coin>, NetworkError>
}