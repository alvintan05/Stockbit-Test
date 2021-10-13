package com.stockbit.local.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.stockbit.local.AppDatabase
import com.stockbit.local.LocalDataSource
import com.stockbit.local.dao.CryptoDao
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE = "DATABASE"

val localModule = module {
//        single(named(DATABASE)) { AppDatabase.buildDatabase(androidContext()) }
//    factory { (get(named(DATABASE)) as AppDatabase).exampleDao() }

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "App.db")
            .build()
    }

    fun provideDao(dataBase: AppDatabase): CryptoDao {
        return dataBase.exampleDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    factory { LocalDataSource(get()) }
}