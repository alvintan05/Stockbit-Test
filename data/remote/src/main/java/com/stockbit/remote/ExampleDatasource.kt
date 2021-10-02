package com.stockbit.remote

import com.stockbit.model.DataItem
import com.stockbit.repository.utils.Resource

/**
 * Implementation of [ExampleService] interface
 */
class ExampleDatasource(private val exampleService: ExampleService) {

    fun fetchTopUsersAsync() =
        exampleService.fetchExampleAsync()

    suspend fun getTopListCrypto(): Resource<List<DataItem?>?> {
        val response = exampleService.getToplistCrypto()
        return if (response.isSuccessful) Resource.success(response.body()?.data)
        else Resource.error(Throwable(), null)
    }

}