package com.haroon.newstestuk.presentation.screens.coindetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroon.newstestuk.data.model.CoinDetails
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(private val repository: CoinRepositoryImpl) : ViewModel() {

    sealed class Command{
        object DataLoading : Command()
        data class DataLoaded(var coinDetails : CoinDetails?) : Command()
    }

    private val _coinDetail = MutableLiveData<CoinDetails>()
    private val _command = SingleMutableStateFlow<Command>(Command.DataLoading)
    val coinDetails: LiveData<CoinDetails>
        get() = _coinDetail

    val command
        get() = _command.stateFlow.stateIn(scope = viewModelScope, started = SharingStarted.Lazily,
            Command.DataLoading)


    fun getCoinById(id: String) = viewModelScope.launch {
        _coinDetail.value = repository.getCoinById(id)
        _command.value = Command.DataLoaded(_coinDetail.value)
    }
}


class SingleMutableStateFlow<T>(initialValue: T) {
    private val _stateFlow = MutableStateFlow(initialValue)
    val stateFlow: StateFlow<T> get() = _stateFlow

    var value: T
        get() = _stateFlow.value
        set(newValue) { _stateFlow.value = newValue }
}





