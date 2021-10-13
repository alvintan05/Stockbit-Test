package com.stockbit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    val price: String,
    @ColumnInfo(name = "change_hour_value")
    val changeHourValue: String,
    @ColumnInfo(name = "change_percentage_hour")
    val changePercentageHour: Double

)