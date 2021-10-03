package com.stockbit.hiring.ui.watchlist

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.databinding.ListItemBinding
import com.stockbit.model.DataItem
import com.stockbit.model.USD

class WatchlistAdapter : RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    private var cryptoList = listOf<DataItem?>()

    fun setList(list: List<DataItem?>?) {
        if (list != null) {
            cryptoList = list
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (cryptoList[position] != null) {
            cryptoList[position]?.let { holder.bindItem(it) }
        }
    }

    override fun getItemCount(): Int = cryptoList.size

    inner class ViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItem(item: DataItem) {
            binding.tvCryptoName.text = item.coinInfo?.name
            binding.tvCryptoFullname.text = item.coinInfo?.fullName
            binding.tvCryptoValue.text = item.display?.usd?.price

            val changePercentage = item.display?.usd?.changePercentageHour
            if (changePercentage == null || changePercentage == 0.0) {
                binding.tvCryptoPercentage.setTextColor(Color.parseColor("#757575"))
                binding.tvCryptoPercentage.text = setupChangeValue(item.display?.usd, "")
            } else {
                when {
                    changePercentage > 0 -> {
                        binding.tvCryptoPercentage.text =
                            setupChangeValue(item.display?.usd, "+")
                        binding.tvCryptoPercentage.setTextColor(Color.parseColor("#00ab6d"))
                    }
                    changePercentage < 0 -> {
                        binding.tvCryptoPercentage.text =
                            setupChangeValue(item.display?.usd, "")
                        binding.tvCryptoPercentage.setTextColor(Color.parseColor("#FF0000"))
                    }
                }
            }
        }

        private fun setupChangeValue(item: USD?, character: String): String {
            return "${
                item?.changeHourValue.toString().replace("$ ", character)
            } ($character${item?.changePercentageHour}%)"
        }
    }


}