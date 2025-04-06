package com.venki.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.venki.cryptotracker.navigation.AdaptiveCoinListDetailPane
import com.venki.cryptotracker.presentation.ObserveAsEvents
import com.venki.cryptotracker.presentation.coin_detail.CoinDetailScreen
import com.venki.cryptotracker.presentation.coin_list.CoinListEvent
import com.venki.cryptotracker.presentation.coin_list.CoinListScreen
import com.venki.cryptotracker.presentation.viewmodel.CoinListViewModel
import com.venki.cryptotracker.ui.theme.CryptoTrackerTheme
import com.venki.cryptotracker.util.toString
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveCoinListDetailPane(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}