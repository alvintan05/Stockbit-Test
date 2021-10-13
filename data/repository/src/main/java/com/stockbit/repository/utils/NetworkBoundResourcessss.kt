package com.stockbit.repository.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class NetworkBoundResourcessss<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            result.value = Resource.Loading()
            val dbSource = loadFromDb()
            result.addSource(dbSource) { data ->
                result.removeSource(dbSource)
                if (shouldFetch(data)) {

                } else {
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.Success(newData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//        val apiResponse = createCall()
//        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
//        result.addSource(dbSource) { newData ->
//            setValue(Resource.Loading(newData))
//        }
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response) {
//                is Resource.Success -> {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        response.data?.let { saveCallResult(it) }
//                        CoroutineScope(Dispatchers.Main).launch {
//                            // we specially request a new live data,
//                            // otherwise we will get immediately last cached value,
//                            // which may not be updated with latest results received from network.
//                            result.addSource(loadFromDb()) { newData ->
//                                setValue(Resource.Success(newData))
//                            }
//                        }
//                    }
//                }
//            }
//            is  -> {
//            appExecutors.mainThread().execute {
//                // reload from disk whatever we had
//                result.addSource(loadFromDb()) { newData ->
//                    setValue(Resource.success(newData))
//                }
//            }
//        }
//            is ApiErrorResponse -> {
//            onFetchFailed()
//            result.addSource(dbSource) { newData ->
//                setValue(Resource.error(response.errorMessage, newData))
//            }
//        }
//
//        }
//    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract suspend fun saveCallResult(item: RequestType)

    // Called with the data in the database to decide whether to fetch
// potentially updated data from the network.
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    // Called to get the cached data from the database.
    @MainThread
    abstract suspend fun loadFromDb(): LiveData<ResultType>

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>

    // Called when the fetch fails. The child class may want to reset components
// like rate limiter.
    protected open fun onFetchFailed() {}
}