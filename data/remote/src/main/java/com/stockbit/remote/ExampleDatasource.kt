package com.stockbit.remote

import com.stockbit.model.CryptoResponse

/**
 * Implementation of [ExampleService] interface
 */
class ExampleDatasource(private val exampleService: ExampleService) {

    fun fetchTopUsersAsync() =
        exampleService.fetchExampleAsync()

    suspend fun getTopListCrypto(): CryptoResponse? {
        val response = exampleService.getToplistCrypto()
        return if (response.isSuccessful) response.body()
        else throw Exception(response.message())
    }

}