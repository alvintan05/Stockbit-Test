package com.stockbit.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.stockbit.model.CryptoEntity
import com.stockbit.model.ExampleModel
import java.util.*

@Dao
abstract class ExampleDao: BaseDao<CryptoEntity>() {

    @Query("SELECT * FROM ExampleModel WHERE name = :name LIMIT 1")
    abstract suspend fun getExample(name: String): ExampleModel

//    suspend fun save(data: ExampleModel) {
//        insert(data)
//    }

    suspend fun save(datas: List<CryptoEntity>) {
        insert(datas)
    }
}