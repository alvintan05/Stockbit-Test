package com.stockbit.hiring.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockbit.model.DataItem
import com.stockbit.repository.ExampleRepository
import com.stockbit.repository.ExampleRepositoryImpl
import com.stockbit.repository.utils.Resource
import kotlinx.coroutines.launch

class WatchlistViewModel (private val repository: ExampleRepositoryImpl) : ViewModel() {

    val data: LiveData<Resource<List<DataItem?>?>> get() = mData

    private val mData = MutableLiveData<Resource<List<DataItem?>?>>().apply {
        value = Resource.loading(null)
    }

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            showLoading()
            val result = repository.getTopListCrypto()
            mData.value = mData.value?.copy(result.status, result.data, result.error)
        }
    }

    private fun showLoading() {
        mData.value = mData.value?.copy(Resource.Status.LOADING, null, null)
    }

}