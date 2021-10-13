package com.stockbit.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stockbit.model.CryptoEntity
import com.stockbit.model.network.DataItem

@Dao
interface CryptoDao {

//    @Query("SELECT * FROM crypto")
//    suspend fun getCryptoList(): PagingSource<Int, CryptoEntity>

    @Query("SELECT * FROM crypto LIMIT :size")
    suspend fun getCryptoList(size: Int): List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<CryptoEntity>)

    @Query("DELETE FROM crypto")
    suspend fun deleteAllCryptoList()

}