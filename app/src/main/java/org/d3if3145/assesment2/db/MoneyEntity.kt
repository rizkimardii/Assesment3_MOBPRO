package org.d3if3145.assesment2.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class MoneyEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var money : Float,
    var mataUang : String,
)
