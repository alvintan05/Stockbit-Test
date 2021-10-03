package com.stockbit.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

/**
 * Implementation of [ExampleService] interface
 */
class ExampleDatasource(private val exampleService: ExampleService) {

    fun fetchTopUsersAsync() =
        exampleService.fetchExampleAsync()

    fun getTopListCrypto() = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 150,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PagingDataSource(exampleService) }
    ).liveData

}