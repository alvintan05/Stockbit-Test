package com.stockbit.remote

import com.stockbit.model.network.CryptoResponse
import com.stockbit.model.ExampleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleService {

    @GET("get/example")
    fun fetchExampleAsync(): Response<ExampleModel>

    @GET("top/totaltoptiervolfull")
    suspend fun getToplistCrypto(
        @Query("limit") limit: Int = 50,
        @Query("tsym") currencySymbol: String = "USD",
        @Query("api_key") apiKey: String = "b0ba20209844ad359af05288e682533915fb92e85d665f72db6ec09edc3c57d7",
        @Query("page") page: Int = 0
    ): Response<CryptoResponse>

}