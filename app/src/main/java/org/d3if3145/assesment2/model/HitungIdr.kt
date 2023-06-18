package org.d3if3145.assesment2.model

import org.d3if3145.assesment2.db.MoneyEntity

fun MoneyEntity.hitungIdr(): HasilRupiah{
    val changer = 14000
    val hasil = money * changer
    return HasilRupiah(hasil)
}