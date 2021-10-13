package com.stockbit.hiring.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.databinding.ListItemBinding
import com.stockbit.model.CryptoEntity

class LocalPagingAdapter : PagingDataAdapter<CryptoEntity, LocalPagingAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocalPagingAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItem(item: CryptoEntity) {
            binding.tvCryptoName.text = item.name
            binding.tvCryptoFullname.text = item.fullName
            binding.tvCryptoValue.text = item.price

            val changePercentage = item.changePercentageHour
            if (changePercentage == 0.0) {
                binding.tvCryptoPercentage.setTextColor(Color.parseColor("#757575"))
                binding.tvCryptoPercentage.text = setupChangeValue(item, "")
            } else {
                when {
                    changePercentage > 0 -> {
                        binding.tvCryptoPercentage.text =
                            setupChangeValue(item, "+")
                        binding.tvCryptoPercentage.setTextColor(Color.parseColor("#00ab6d"))
                    }
                    changePercentage < 0 -> {
                        binding.tvCryptoPercentage.text =
                            setupChangeValue(item, "")
                        binding.tvCryptoPercentage.setTextColor(Color.parseColor("#FF0000"))
                    }
                }
            }
        }

        private fun setupChangeValue(item: CryptoEntity, character: String): String {
            return "${
                item.changeHourValue.replace("$ ", character)
            } ($character${item.changePercentageHour}%)"
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CryptoEntity>() {
            override fun areItemsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}