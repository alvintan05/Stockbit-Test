package com.stockbit.repository

import com.stockbit.local.dao.ExampleDao
import com.stockbit.model.DataItem
import com.stockbit.model.ExampleModel
import com.stockbit.remote.ExampleDatasource
import com.stockbit.repository.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ExampleRepository {
    suspend fun getExample(): Flow<Resource<ExampleModel>>
    suspend fun getTopListCrypto(): Resource<List<DataItem?>?>
}

class ExampleRepositoryImpl(
    private val datasource: ExampleDatasource,
    private val dao: ExampleDao
) : ExampleRepository {

    override suspend fun getExample(): Flow<Resource<ExampleModel>> {
        return flow { }
    }

    override suspend fun getTopListCrypto(): Resource<List<DataItem?>?> {
        val responseData = datasource.getTopListCrypto()
        return if (responseData != null) {
            Resource.success(responseData.data)
        } else {
            Resource.error(Throwable(), null)
        }
    }

}