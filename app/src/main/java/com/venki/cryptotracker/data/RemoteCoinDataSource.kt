package com.venki.cryptotracker.data

import com.venki.cryptotracker.BuildConfig
import com.venki.cryptotracker.data.dto.CoinResponseDto
import com.venki.cryptotracker.data.dto.toCoin
import com.venki.cryptotracker.domain.Coin
import com.venki.cryptotracker.domain.CoinDataSource
import com.venki.cryptotracker.domain.NetworkError
import com.venki.cryptotracker.domain.Result
import com.venki.cryptotracker.domain.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCointDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}

fun constructUrl(url : String): String{
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}