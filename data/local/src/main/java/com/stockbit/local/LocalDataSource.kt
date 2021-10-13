package com.stockbit.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.stockbit.local.dao.CryptoDao
import com.stockbit.model.CryptoEntity

class LocalDataSource(private val dao: CryptoDao) {

    fun getCryptoList() = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 150,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CryptoPagingDataSource(dao) }
    ).liveData

    suspend fun insert(data: List<CryptoEntity>) = dao.insert(data)
    suspend fun deleteAll() = dao.deleteAllCryptoList()
}