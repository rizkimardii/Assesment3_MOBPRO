package org.d3if3145.assesment2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MoneyEntity::class], version = 1, exportSchema = false)
abstract class MoneyDb : RoomDatabase() {
    abstract val dao: MoneyDao
    companion object {
        @Volatile
        private var INSTANCE: MoneyDb? = null
        fun getInstance(context: Context): MoneyDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoneyDb::class.java,
                        "data.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}