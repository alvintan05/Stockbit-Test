package com.stockbit.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stockbit.model.CryptoEntity
import com.stockbit.model.network.DataItem
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_INDEX = 0

class PagingDataSource(
    private val exampleService: ExampleService
) : PagingSource<Int, CryptoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoEntity> {
        val position = params.key ?: PAGE_INDEX

        return try {
            val response = exampleService.getToplistCrypto(page = position)
            val data = response.body()?.data ?: listOf(DataItem())
            val convertData = arrayListOf<CryptoEntity>()
            for (item in data) {
                val entity = CryptoEntity(
                    item.coinInfo?.id ?: 0,
                    item.coinInfo?.name ?: "",
                    item.coinInfo?.fullName ?: "",
                    item.display?.usd?.price ?: "",
                    item.display?.usd?.changeHourValue ?: "",
                    item.display?.usd?.changePercentageHour ?: 0.0
                )
                convertData.add(entity)
            }
            LoadResult.Page(
                data = convertData,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CryptoEntity>): Int = PAGE_INDEX
}