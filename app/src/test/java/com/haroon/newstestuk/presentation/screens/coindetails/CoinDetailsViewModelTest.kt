package com.haroon.newstestuk.presentation.screens.coindetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import com.haroon.newstestuk.data.model.CoinDetails
import com.haroon.newstestuk.data.model.Links
import com.haroon.newstestuk.data.model.ParentCoin
import com.haroon.newstestuk.data.model.Whitepaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CoinDetailsViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CoinDetailsViewModel

    @Mock
    private lateinit var mockRepository: CoinRepositoryImpl

    private val testCoinDetails = CoinDetails(
        id = "1",
        name = "Bitcoin",
        symbol = "BTC",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "coin",
        contract = "",
        platform = "",
        contracts = emptyList(),
        logo = "",
        parent = ParentCoin("", "", ""),
        tags = emptyList(),
        team = emptyList(),
        description = "",
        message = null,
        openSource = false,
        startedAt = null,
        developmentStatus = null,
        hardwareWallet = false,
        proofType = null,
        orgStructure = null,
        hashAlgorithm = null,
        links = Links(listOf()),
        linksExtended = emptyList(),
        whitepaper = Whitepaper("",""),
        firstDataAt = null,
        lastDataAt = null
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CoinDetailsViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCoinByIdReturnsDataSuccessfully() = runBlockingTest {
        Mockito.`when`(mockRepository.getCoinById("1")).thenReturn(testCoinDetails)

        viewModel.getCoinById("1")

        assertEquals(testCoinDetails, viewModel.coinDetails.value)
        assertEquals(CoinDetailsViewModel.Command.DataLoaded(testCoinDetails), viewModel.command.value)
    }

    @Test
    fun testGetCoinByIdHandlesUnknownHostException() = testDispatcher.runBlockingTest  {
        Mockito.`when`(mockRepository.getCoinById("1")).thenThrow(RuntimeException())

        viewModel.getCoinById("1")

        assertEquals(CoinDetailsViewModel.Command.DataLoadingFailed, viewModel.command.value)
    }

    @Test
    fun testGetCoinByIdHandlesNullResponse() = testDispatcher.runBlockingTest  {
        Mockito.`when`(mockRepository.getCoinById("1")).thenReturn(null)

        viewModel.getCoinById("1")

        assertEquals(CoinDetailsViewModel.Command.DataLoadingFailed, viewModel.command.value)
    }

    @Test
    fun testGetCoinByIdReturnsDefaultData() = testDispatcher.runBlockingTest  {
        Mockito.`when`(mockRepository.getCoinById("1")).thenReturn(testCoinDetails)

        viewModel.getCoinById("1")

        assertEquals(testCoinDetails, viewModel.coinDetails.value)
        assertEquals(CoinDetailsViewModel.Command.DataLoaded(testCoinDetails), viewModel.command.value)
    }

    @Test
    fun testGetCoinByIdHandlesOtherExceptions() = testDispatcher.runBlockingTest  {
        Mockito.`when`(mockRepository.getCoinById("1")).thenThrow(RuntimeException::class.java)

        viewModel.getCoinById("1")

        assertEquals(CoinDetailsViewModel.Command.DataLoadingFailed, viewModel.command.value)
    }

    @Test
    fun testGetCoinByIdHandlesMultipleCalls() = testDispatcher.runBlockingTest  {

        Mockito.`when`(mockRepository.getCoinById("1")).thenReturn(testCoinDetails)
        Mockito.`when`(mockRepository.getCoinById("2")).thenReturn(testCoinDetails)

        viewModel.getCoinById("1")
        viewModel.getCoinById("2")

        assertEquals(testCoinDetails, viewModel.coinDetails.value)
        assertEquals(CoinDetailsViewModel.Command.DataLoaded(testCoinDetails), viewModel.command.value)
    }


}
