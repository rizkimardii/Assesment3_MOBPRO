package org.d3if3145.assesment2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import org.d3if3145.assesment2.databinding.ItemInformasiBinding
import org.d3if3145.assesment2.model.Uang
import org.d3if3145.assesment2.network.UangApi


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<Uang>()
    fun updateData(newData: List<Uang>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemInformasiBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uang: Uang) = with(binding) {
            mataUangTextView.text = uang.nama
            informasiTextView.text = uang.informasi
            Glide.with(imageView.context)
                .load(UangApi.getUangUrl(uang.gambar))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)


            root.setOnClickListener {
                val message = root.context.getString(R.string.message, uang.nama)
                Toast.makeText(root.context, uang.nama, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInformasiBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}