package com.zaki.kasirapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zaki.kasirapp.databinding.FragmentDetailBinding
import com.zaki.kasirapp.databinding.ItemDetailBinding
import com.zaki.kasirapp.databinding.ItemKasirBinding
import com.zaki.kasirapp.model.Detail

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    class DetailViewHolder(binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var binding: ItemDetailBinding

    private val differCallback = object : DiffUtil.ItemCallback<Detail>() {
        override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailAdapter.DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val detail = differ.currentList[position]
        holder.itemView.apply {
            binding.tvNamaDetail.text = detail.nama
            binding.tvJumlahDetail.text = detail.qty.toString()
            binding.tvHargaDetail.text = detail.qty.toString()
            binding.tvTotalDetail.text = String.format("Rp. %s", (detail.qty * detail.harga).toString())
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}