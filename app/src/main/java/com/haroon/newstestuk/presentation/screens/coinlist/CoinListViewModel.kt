package com.haroon.newstestuk.presentation.screens.coinlist

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroon.newstestuk.data.model.Coin
import com.haroon.newstestuk.data.repository.CoinRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinRepositoryImpl) : ViewModel() {

    sealed class Command{
        object DataLoading : Command()
        object DataLoadingFailed : Command()
        data class DataLoaded(var coins : List<Coin>?) : Command()
    }


    private val _coins = MutableLiveData<List<Coin>>()
    private val _selectedType = MutableLiveData<String>()
    private val _command = MutableStateFlow<Command>(Command.DataLoading)

    val coins: LiveData<List<Coin>>
        get() = _coins

    val selectedType : LiveData<String>
        get() = _selectedType


    val command: StateFlow<Command> = _command


    init {
        _command.value = Command.DataLoading
        _selectedType.value = "all"
        getCoins()
    }


    fun getCoins() = viewModelScope.launch {
        try {
            _coins.value = repository.getCoins()
        } catch (exception: Exception)  {
            _command.value = Command.DataLoadingFailed
        }
        _coins.value?.let {
            _coins.value = it.sortedBy { item -> item.name }
            _command.value = Command.DataLoaded(_coins.value)
        } ?: run {
            _command.value = Command.DataLoadingFailed
        }


    }

    fun refreshCoins() {
        _command.value = Command.DataLoading
        getCoins()
    }

    fun onTypeChange(type : String){
        _selectedType.value = type.lowercase()
        when (type.lowercase()){
            "all" -> {
                _command.value = Command.DataLoaded(_coins.value)
            }
            "coin" -> {
                _command.value  = Command.DataLoaded(_coins.value?.filter { it.type.lowercase() == "coin" })
            }
            "token" -> {
                _command.value  = Command.DataLoaded(_coins.value?.filter { it.type.lowercase() == "token" })
            }
        }
    }
}
