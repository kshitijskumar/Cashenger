package com.example.cashenger.domain.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cashenger.domain.db.dao.RecordsDao
import com.example.cashenger.domain.records.models.IncomeExpenseModel

@Database(entities = [IncomeExpenseModel::class], version = 1)
abstract class RecordsDb : RoomDatabase() {

    abstract fun getRecordsDao() : RecordsDao

    companion object {

        @Volatile
        private var INSTANCE: RecordsDb? = null

        fun getRecordsDatabase(context: Context) : RecordsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordsDb::class.java,
                    "records_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}