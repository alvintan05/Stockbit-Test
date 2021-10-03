package com.stockbit.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stockbit.model.DataItem
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_INDEX = 0

class PagingDataSource(
    private val exampleService: ExampleService
) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int = PAGE_INDEX

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: PAGE_INDEX

        return try {
            val response = exampleService.getToplistCrypto(page = position)
            val data = response.body()?.data ?: listOf(DataItem())
            LoadResult.Page(
                data = data,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}