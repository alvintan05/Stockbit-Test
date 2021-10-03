package com.stockbit.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.stockbit.local.dao.ExampleDao
import com.stockbit.model.DataItem
import com.stockbit.model.ExampleModel
import com.stockbit.remote.ExampleDatasource
import com.stockbit.repository.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ExampleRepository {
    suspend fun getExample(): Flow<Resource<ExampleModel>>
    suspend fun getTopListCrypto(): LiveData<PagingData<DataItem>>
}

class ExampleRepositoryImpl(
    private val datasource: ExampleDatasource,
    private val dao: ExampleDao
) : ExampleRepository {

    override suspend fun getExample(): Flow<Resource<ExampleModel>> {
        return flow { }
    }

    override suspend fun getTopListCrypto(): LiveData<PagingData<DataItem>> =
        datasource.getTopListCrypto()

}