package com.stockbit.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.stockbit.local.LocalDataSource
import com.stockbit.model.CryptoEntity
import com.stockbit.model.ExampleModel
import com.stockbit.model.network.DataItem
import com.stockbit.remote.ExampleDatasource
import com.stockbit.repository.utils.NetworkBoundResource
import com.stockbit.repository.utils.Resource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

interface ExampleRepository {
    suspend fun getExample(): Flow<Resource<ExampleModel>>

    //    suspend fun getTopListCrypto(): LiveData<PagingData<DataItem>>
    suspend fun getTopListCrypto(): LiveData<PagingData<CryptoEntity>>
    suspend fun getListFromRoom(): LiveData<PagingData<CryptoEntity>>
}

class ExampleRepositoryImpl(
    private val datasource: ExampleDatasource,
    private val localDataSource: LocalDataSource
) : ExampleRepository {

    override suspend fun getExample(): Flow<Resource<ExampleModel>> {
        return flow { }
    }

//    override suspend fun getTopListCrypto(): LiveData<PagingData<DataItem>> =
//        datasource.getTopListCrypto()

    override suspend fun getTopListCrypto(): LiveData<PagingData<CryptoEntity>> =
        datasource.getTopListCrypto()

    override suspend fun getListFromRoom(): LiveData<PagingData<CryptoEntity>> =
        localDataSource.getCryptoList()
//
//    fun getLists() =
//        object :
//            NetworkBoundResource<LiveData<PagingData<CryptoEntity>>, LiveData<PagingData<CryptoEntity>>>(
//                true,
//                Dispatchers.IO
//            ) {
//            override fun loadFromDb(): LiveData<LiveData<PagingData<CryptoEntity>>> {
//                return localDataSource.getCryptoList()
//            }
//
//            override suspend fun saveCallResult(item: LiveData<PagingData<CryptoEntity>>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun shouldFetch(data: LiveData<PagingData<CryptoEntity>>?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun createCall(): Resource<LiveData<PagingData<CryptoEntity>>> {
//                TODO("Not yet implemented")
//            }
//
//
//        }.asLiveData()

}