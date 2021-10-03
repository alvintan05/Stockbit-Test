package com.stockbit.hiring.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stockbit.model.DataItem
import com.stockbit.repository.ExampleRepositoryImpl
import kotlinx.coroutines.launch

class WatchlistViewModel(private val repository: ExampleRepositoryImpl) : ViewModel() {

    lateinit var data: LiveData<PagingData<DataItem>>

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        val result = repository.getTopListCrypto().cachedIn(viewModelScope)
        data = result
    }

}