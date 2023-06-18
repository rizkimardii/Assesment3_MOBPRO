package org.d3if3145.assesment2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3145.assesment2.db.MoneyDao
import org.d3if3145.assesment2.db.MoneyEntity
import org.d3if3145.assesment2.model.HasilEuro
import org.d3if3145.assesment2.model.HasilRupiah
import org.d3if3145.assesment2.model.hitungEuro
import org.d3if3145.assesment2.model.hitungIdr

class HitungViewModel(private val db: MoneyDao) : ViewModel() {
    private val hasilIdr = MutableLiveData<HasilRupiah?>()
    private val euro = MutableLiveData<HasilEuro?>()


     fun hitungIdr(money : Float) {

         val data = MoneyEntity(
             money = money,
             mataUang = "IDR"
         )
         hasilIdr.value = data.hitungIdr()

         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val data = MoneyEntity(
                     money = money,
                     mataUang = "USD"

                 )
                 db.insert(data)
             }
         }
    }

    fun hitungEuro(money: Float) {

        val data = MoneyEntity(
            money = money,
            mataUang = "EUR"
        )
        euro.value = data.hitungEuro()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                db.insert(data)
            }
        }
    }

    fun getHasilEuro(): LiveData<HasilEuro?> = euro
    fun getHasilIdr(): LiveData<HasilRupiah?> = hasilIdr
}