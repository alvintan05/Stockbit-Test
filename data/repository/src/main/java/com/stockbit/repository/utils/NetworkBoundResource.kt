package com.stockbit.repository.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
abstract class NetworkBoundResource<ResultType, RequestType>(
    private val shouldLoad: Boolean = true,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

//    private val result: LiveData<Resource<ResultType>> = liveData(dispatcher) {
//        val disposable = emitSource(
//            loadFromDb().map {
//                Resource.Loading(it)
//            }
//        )
//        if (shouldFetch()) {
//            val apiResponse = createCall()
//            when (apiResponse) {
//                is Resource.Success -> {
//                    apiResponse.data?.let { saveCallResult(it) }
//                    emitSource(loadFromDb().map { Resource.Success(it) })
//                }
//                is Resource.Loading -> {
//                    emitSource(loadFromDb().map { Resource.Loading(it) })
//                }
//                is Resource.Error -> {
//                    onFetchFailed()
//                }
//            }
//        } else {
//            emitSource(loadFromDb().map { Resource.Success(it) })
//        }
//    }

    open fun shouldFetch(): Boolean {
        return shouldLoad
    }

    @WorkerThread
    abstract fun loadFromDb(): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun createCall(): Resource<RequestType>

    protected open fun onFetchFailed() {}

//    fun asLiveData() = result

    @MainThread
    abstract fun getRequestAsync(): Deferred<Response<RequestType>>
}