package com.stockbit.hiring.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stockbit.model.CryptoEntity
import com.stockbit.model.network.DataItem
import com.stockbit.repository.ExampleRepositoryImpl
import kotlinx.coroutines.launch

class WatchlistViewModel(private val repository: ExampleRepositoryImpl) : ViewModel() {

    //    lateinit var data: LiveData<PagingData<DataItem>>
    lateinit var data: LiveData<PagingData<CryptoEntity>>

    init {
//        getLocalData()
        getData()
    }

    private fun getData() = viewModelScope.launch {
        val result = repository.getTopListCrypto().cachedIn(viewModelScope)
        data = result
    }

//    private fun getLocalData() = viewModelScope.launch {
//        val result = repository.getListFromRoom().cachedIn(viewModelScope)
//        data = result
//    }

}