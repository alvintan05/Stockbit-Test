package com.stockbit.remote

import com.stockbit.model.CryptoResponse
import com.stockbit.model.ExampleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleService {

    @GET("get/example")
    fun fetchExampleAsync(): Response<ExampleModel>

    @GET("top/totaltoptiervolfull")
    suspend fun getToplistCrypto(
        @Query("limit") limit: Int = 20,
        @Query("tsym") currencySymbol: String = "USD"
    ): Response<CryptoResponse>

}