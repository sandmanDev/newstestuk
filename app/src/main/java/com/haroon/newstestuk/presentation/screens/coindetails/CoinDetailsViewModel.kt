package com.haroon.newstestuk.presentation.screens.coindetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroon.newstestuk.data.model.CoinDetails
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import com.haroon.newstestuk.presentation.screens.coinlist.CoinListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(private val repository: CoinRepositoryImpl) : ViewModel() {

    sealed class Command{
        object DataLoading : Command()

        object DataLoadingFailed : Command()
        data class DataLoaded(var coinDetails : CoinDetails?) : Command()
    }

    private val _coinDetail = MutableLiveData<CoinDetails>()
    private val _command = MutableStateFlow<Command>(Command.DataLoading)
    val coinDetails: LiveData<CoinDetails>
        get() = _coinDetail

    val command: StateFlow<Command> = _command


    fun getCoinById(id: String) = viewModelScope.launch {
        try {
            _coinDetail.value = repository.getCoinById(id)
        } catch (exception: Exception)  {
            _command.value = Command.DataLoadingFailed
        }

        _coinDetail.value?.let {
            _command.value = Command.DataLoaded(_coinDetail.value)
        } ?: run {
            _command.value = Command.DataLoadingFailed
        }

    }
}






