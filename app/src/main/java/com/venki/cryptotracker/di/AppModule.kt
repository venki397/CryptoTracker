package com.venki.cryptotracker.di

import com.venki.cryptotracker.data.HttpClientFactory
import com.venki.cryptotracker.data.RemoteCointDataSource
import com.venki.cryptotracker.domain.CoinDataSource
import com.venki.cryptotracker.presentation.viewmodel.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCointDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}