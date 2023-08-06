package com.haroon.newstestuk.presentation.screens.coindetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.haroon.newstestuk.presentation.screens.coindetails.components.CoinDetailsView


@Composable
fun CoinDetailsScreen(navController: NavController, coinID: String?) {
    val viewModel = hiltViewModel<CoinDetailsViewModel>()
    val state by viewModel.command.collectAsState()

    if (coinID != null) {
        viewModel.getCoinById(coinID)
    }
    when (state) {
        is CoinDetailsViewModel.Command.DataLoaded -> {
            (state as CoinDetailsViewModel.Command.DataLoaded)?.coinDetails?.let {
                CoinDetailsView(navController,it)
            }
        }
        CoinDetailsViewModel.Command.DataLoading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        null -> TODO()
    }


}