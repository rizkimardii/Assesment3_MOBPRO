package org.d3if3145.assesment2.ui.histori

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3145.assesment2.R
import org.d3if3145.assesment2.databinding.ItemHistoriBinding
import org.d3if3145.assesment2.db.MoneyEntity
import org.d3if3145.assesment2.model.hitungEuro
import org.d3if3145.assesment2.model.hitungIdr
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :

    ListAdapter<MoneyEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MoneyEntity>() {
                override fun areItemsTheSame(
                    oldData: MoneyEntity, newData: MoneyEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: MoneyEntity, newData: MoneyEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        @SuppressLint("StringFormatMatches")
        fun bind(item: MoneyEntity) = with(binding) {
            val hasilRupiah= item.hitungIdr()
            val hasilEuro = item.hitungEuro()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))

            if(item.mataUang.equals("USD")){
                hasilTextView.text = root.context.getString(R.string.data_z,
                    hasilRupiah.hasil)
                dataTextView.text = root.context.getString(R.string.mata_uang,
                    item.mataUang)
                val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, R.color.histori_usd))
            }else{
                dataTextView.text = root.context.getString(R.string.mata_uang,
                    item.mataUang)
                hasilTextView.text = root.context.getString(R.string.data_z,
                    hasilEuro.jumlahEuro)
                val circleBg = kategoriTextView.background as GradientDrawable
                circleBg.setColor(ContextCompat.getColor(root.context, R.color.histori_eur))
            }

        }
    }
}