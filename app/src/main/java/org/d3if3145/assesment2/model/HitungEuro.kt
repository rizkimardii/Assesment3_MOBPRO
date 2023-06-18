package org.d3if3145.assesment2.model

import org.d3if3145.assesment2.db.MoneyEntity

fun MoneyEntity.hitungEuro(): HasilEuro{
    val changer = 17000
    val hasil = money * changer
    return HasilEuro(hasil)
}
