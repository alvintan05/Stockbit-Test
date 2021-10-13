package com.stockbit.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stockbit.model.CryptoEntity
import com.stockbit.model.ExampleModel

abstract class BaseDao<T> {

    @Query("SELECT * FROM crypto")
    protected abstract fun getCryptoList(): LiveData<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insert(data: List<T>)

    @Query("DELETE FROM crypto")
    protected abstract fun deleteAllCryptoList()

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    protected abstract suspend fun insert(user: T)
}