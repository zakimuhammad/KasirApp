package com.zaki.kasirapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zaki.kasirapp.databinding.ItemListPenjualanBinding
import com.zaki.kasirapp.model.Penjualan

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    class ListViewHolder(binding: ItemListPenjualanBinding): RecyclerView.ViewHolder(binding.root)

    private lateinit var binding: ItemListPenjualanBinding

    private val differCallback = object : DiffUtil.ItemCallback<Penjualan>() {
        override fun areItemsTheSame(old: Penjualan, aNew: Penjualan): Boolean {
            return old.id == aNew.id
        }

        override fun areContentsTheSame(old: Penjualan, aNew: Penjualan): Boolean {
            return old == aNew
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemListPenjualanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.itemView.apply {
            binding.tvTanggal.text = list.tanggal
            binding.tvHargaListPenjualan.text = String.format("Rp. %s", list.harga.toString())
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Penjualan) -> Unit)? = null

    fun setOnItemClickListener(listener: (Penjualan) -> Unit) {
        onItemClickListener = listener
    }
}