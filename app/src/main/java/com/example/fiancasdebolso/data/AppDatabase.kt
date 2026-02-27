package com.example.fiancasdebolso.data

import com.example.fiancasdebolso.data.local.dao.CategoryDao
import com.example.fiancasdebolso.data.local.dao.TransactionDao
import com.example.fiancasdebolso.data.local.entity.CategoryEntity
import com.example.fiancasdebolso.data.local.entity.TransactionEntity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TransactionEntity::class, CategoryEntity::class],
    version = 1
)

abstract  class  AppDatabase : RoomDatabase(){

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "financas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}
