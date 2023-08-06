package com.haroon.newstestuk.presentation.screens.coindetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.haroon.newstestuk.data.model.CoinDetails
import com.haroon.newstestuk.presentation.common.CustomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailsView(navController: NavController, coin: CoinDetails) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        scaffoldState = scaffoldState,
        topBar = {

            CustomAppBar(coin.name,true) {
                navController.popBackStack()
            }
        },
        content = {paddingValues -> Modifier.padding(paddingValues)
            CoinDetailsContent(coin = coin)
        }
    )
}

@Composable
fun CoinDetailsContent(coin: CoinDetails?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Coin Name: ${coin?.name ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Coin Symbol: ${coin?.symbol ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Coin Rank: ${coin?.rank?.toString() ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Platform: ${coin?.platform ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Type: ${coin?.type ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Contract: ${coin?.contract ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Is Active: ${coin?.isActive?.toString() ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Is New: ${coin?.isNew?.toString() ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))

        coin?.tags?.let {
            Text(text = "Tags:")
            Spacer(modifier = Modifier.height(4.dp))
            it.forEach { tag ->
                Text(text = "- ${tag.name}")
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    }
}

