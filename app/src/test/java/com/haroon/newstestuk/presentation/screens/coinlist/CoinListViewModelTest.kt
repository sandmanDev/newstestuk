package com.haroon.newstestuk.presentation.screens.coinlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.haroon.newstestuk.TestCoroutineRule
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import com.haroon.newstestuk.data.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CoinListViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CoinListViewModel
    private lateinit var mockRepository: CoinRepositoryImpl

    @Before
    fun setup() {
        mockRepository = mock(CoinRepositoryImpl::class.java)
        viewModel = CoinListViewModel(mockRepository)
    }

    @Test
    fun `test getCoins fetches and sorts data`() = runBlockingTest {
        val mockData = listOf(
            Coin("2", "ZCoin", "ZCO", 2, false, true, "type1"),
            Coin("1", "ACoin", "ACO", 1, false, true, "type1")
        )
        Mockito.`when`(mockRepository.getCoins()).thenReturn(mockData)
        viewModel.refreshCoins()

        val result = viewModel.coins.value
        assert(result?.get(0)?.id == "1")
    }

    @Test
    fun `test onTypeChange changes the type value`() {
        viewModel.onTypeChange("coin")
        assertEquals(viewModel.selectedType.value, "coin")
    }

    @Test
    fun `test if coins are loaded successfully`() = runBlocking {
        val coins = listOf(
            Coin("1", "Bitcoin", "BTC", 1, false, true, "coin"),
            Coin("2", "Ethereum", "ETH", 2, false, true, "coin")
        )
        `when`(mockRepository.getCoins()).thenReturn(coins)

        viewModel = CoinListViewModel(mockRepository)

        val observer = mock(Observer::class.java) as Observer<List<Coin>>
        viewModel.coins.observeForever(observer)

        viewModel.getCoins()

        val expectedCoins = coins.sortedBy { it.name }
        assertEquals(expectedCoins, viewModel.coins.value)
    }

    @Test
    fun `test onTypeChange function with "token" type`() = testDispatcher.runBlockingTest {
        val coins = listOf(
            Coin("1", "Bitcoin", "BTC", 1, false, true, "coin"),
            Coin("2", "Ethereum", "ETH", 2, false, true, "coin"),
            Coin("3", "Token A", "TKA", 3, true, true, "token"),
            Coin("4", "Token B", "TKB", 4, true, true, "token")
        )

        `when`(mockRepository.getCoins()).thenReturn(coins)

        viewModel = CoinListViewModel(mockRepository)
        viewModel.getCoins()
        viewModel.onTypeChange("token")

        val expectedCoins = coins.filter { it.type.lowercase() == "token" }
        val expectedCommand = CoinListViewModel.Command.DataLoaded(expectedCoins)
        assertEquals(expectedCommand, viewModel.command.value)
    }


    @Test
    fun `test if coins loading failed`() = testDispatcher.runBlockingTest {
        `when`(mockRepository.getCoins()).thenThrow(RuntimeException())

        viewModel.getCoins()

        assertEquals(CoinListViewModel.Command.DataLoadingFailed, viewModel.command.value)
    }
}
