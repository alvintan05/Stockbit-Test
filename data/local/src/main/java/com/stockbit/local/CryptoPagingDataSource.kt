package com.stockbit.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stockbit.local.dao.CryptoDao
import com.stockbit.model.CryptoEntity
import java.io.IOException

private const val PAGE_INDEX = 0

class CryptoPagingDataSource(private val dao: CryptoDao) : PagingSource<Int, CryptoEntity>() {

    override fun getRefreshKey(state: PagingState<Int, CryptoEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoEntity> {
        val position = params.key ?: PAGE_INDEX

        return try {
            val response = dao.getCryptoList(params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }

    }
}