package com.venki.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venki.cryptotracker.domain.CoinDataSource
import com.venki.cryptotracker.domain.onError
import com.venki.cryptotracker.domain.onSuccess
import com.venki.cryptotracker.presentation.coin_list.CoinListEvent
import com.venki.cryptotracker.presentation.coin_list.CoinListState
import com.venki.cryptotracker.presentation.models.CoinListActions
import com.venki.cryptotracker.presentation.models.toCoinUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            loadCoins()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(actions: CoinListActions){
        when(actions){
            is CoinListActions.OnCoinClick -> {
                _state.update { it.copy(
                    selectedCoin = actions.coinUi
                ) }
            }
        }
    }


    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource.getCoins().onSuccess { coins ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        coins = coins.map { it.toCoinUI() })
                }

            }.onError { networkError ->
                _state.update { it.copy(isLoading = false) }
                _events.send(CoinListEvent.Error(networkError))
            }
        }
    }
}