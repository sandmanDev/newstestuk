package com.haroon.newstestuk.presentation.screens.coinlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroon.newstestuk.TestCoroutineRule
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import com.haroon.newstestuk.data.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class CoinListViewModelTest {

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
}
