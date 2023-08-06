package com.haroon.newstestuk.presentation.screens.coinlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.haroon.newstestuk.presentation.common.CustomAppBar
import com.haroon.newstestuk.presentation.screens.coinlist.components.CoinList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(navController: NavController) {
    val viewModel = hiltViewModel<CoinListViewModel>()
    val state by viewModel.command.collectAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = MaterialTheme.colorScheme.background, scaffoldState = scaffoldState, topBar = {
        CustomAppBar("Coins",false,null)
    }) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (state) {
                is CoinListViewModel.Command.DataLoaded -> {
                    (state as CoinListViewModel.Command.DataLoaded)?.coins?.let {
                        CoinList(
                            coins = it,
                            onCoinClick = {
                                navController.navigate("coinDetailsFragment/${it.id}")
                            },
                            onRefresh = { viewModel.refreshCoins() },
                            onTypeChange = {
                                viewModel.onTypeChange(it)
                            },
                            selectedType = viewModel.selectedType.value!!
                        )
                    }
                }
                CoinListViewModel.Command.DataLoading -> {
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

    }
}






