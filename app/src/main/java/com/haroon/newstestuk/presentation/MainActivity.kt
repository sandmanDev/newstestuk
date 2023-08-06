package com.haroon.newstestuk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haroon.newstestuk.presentation.common.MainDestinations
import com.haroon.newstestuk.presentation.screens.coindetails.CoinDetailsScreen
import com.haroon.newstestuk.presentation.screens.coinlist.CoinListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MaterialTheme(colors = darkColors()) {
                NavHost(
                    navController,
                    MainDestinations.COIN_LIST_ROUTE,
                    Modifier.fillMaxSize(),
                ) {

                    composable(MainDestinations.COIN_LIST_ROUTE) {
                        CoinListScreen(navController = navController)
                    }
                    composable(MainDestinations.COIN_DETAILS_ROUTE) { backStackEntry ->
                        val coinId = backStackEntry.arguments?.getString(MainDestinations.CoinDetailsArgs.COIN_ID) ?: ""
                        CoinDetailsScreen(navController = navController, coinID = coinId)
                    }
                }
            }

        }
    }
}
