package org.d3if3145.assesment2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoneyDao {
    @Insert
    fun insert(data: MoneyEntity)
    @Query("SELECT * FROM data ORDER BY id DESC")
    fun getLastData(): LiveData<List<MoneyEntity>>
    @Query("DELETE FROM data")
    fun clearData()
}