package com.zaki.kasirapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zaki.kasirapp.databinding.ItemKasirBinding
import com.zaki.kasirapp.model.Kasir

class KasirAdapter : RecyclerView.Adapter<KasirAdapter.KasirViewHolder>() {
    private lateinit var binding: ItemKasirBinding

    class KasirViewHolder(binding: ItemKasirBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Kasir>() {
        override fun areItemsTheSame(old: Kasir, aNew: Kasir): Boolean {
            return old.id == aNew.id
        }

        override fun areContentsTheSame(old: Kasir, aNew: Kasir): Boolean {
            return old == aNew
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KasirViewHolder {
        binding = ItemKasirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KasirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KasirViewHolder, position: Int) {
        val kasir = differ.currentList[position]
        holder.itemView.apply {
            binding.tvNamaProduk.text = kasir.nama
            binding.tvHargaProduk.text = kasir.harga.toString()
            tag = kasir.harga.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}