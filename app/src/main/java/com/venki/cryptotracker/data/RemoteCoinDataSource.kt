package com.venki.cryptotracker.data

import com.venki.cryptotracker.BuildConfig
import com.venki.cryptotracker.data.dto.CoinPriceHistoryDto
import com.venki.cryptotracker.data.dto.CoinResponseDto
import com.venki.cryptotracker.data.dto.toCoin
import com.venki.cryptotracker.data.dto.toCoinPrice
import com.venki.cryptotracker.domain.Coin
import com.venki.cryptotracker.domain.CoinDataSource
import com.venki.cryptotracker.domain.CoinPrice
import com.venki.cryptotracker.domain.NetworkError
import com.venki.cryptotracker.domain.Result
import com.venki.cryptotracker.domain.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCointDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            ){
                parameter("apiKey", BuildConfig.API_KEY)
                parameter("limit",50)
            }
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        return safeCall<CoinPriceHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ){
                parameter("apiKey", BuildConfig.API_KEY)
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
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