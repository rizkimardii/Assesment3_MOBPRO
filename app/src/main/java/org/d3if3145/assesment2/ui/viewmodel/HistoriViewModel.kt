package org.d3if3145.assesment2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3145.assesment2.db.MoneyDao

class HistoriViewModel(private val db: MoneyDao) : ViewModel() {
    val data = db.getLastData()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}