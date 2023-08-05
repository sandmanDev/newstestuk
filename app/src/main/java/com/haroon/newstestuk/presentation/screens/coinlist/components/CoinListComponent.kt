package com.haroon.newstestuk.presentation.screens.coinlist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haroon.newstestuk.data.model.Coin
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.haroon.newstestuk.presentation.common.ChipGroup


@Composable
fun CoinList(
    coins: List<Coin>,
    onCoinClick: (Coin) -> Unit,
    onRefresh: () -> Unit,
    onTypeChange: (String) -> Unit,
    selectedType: String
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        swipeRefreshState.isRefreshing = false
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
    ) {
        Column {

            ChipGroup(listOf("All", "Coin", "Token"), selectedType) {
                onTypeChange(it)
            }

            LazyColumn(state = listState) {
                items(coins) { coin ->
                    AnimatedCoinListItem(coin = coin, onCoinClick = onCoinClick)
                    Divider(color = Color.LightGray, modifier = Modifier.height(1.dp))
                }
            }
        }
    }
}

@Composable
fun AnimatedCoinListItem(coin: Coin, onCoinClick: (Coin) -> Unit) {
    val enterTransition = remember { fadeIn() + slideInHorizontally() }
    val exitTransition = remember { fadeOut() + slideOutHorizontally() }

    AnimatedVisibility(
        visible = true,
        enter = enterTransition,
        exit = exitTransition
    ) {
        CoinListItem(coin = coin, onCoinClick = onCoinClick)
    }
}

@Composable
fun CoinListItem(coin: Coin, onCoinClick: (Coin) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCoinClick(coin) }
            .height(50.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = coin.name,
            fontSize = 18.sp
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Coin Details",
            tint = MaterialTheme.colors.primary
        )
    }
}
