package com.stockbit.hiring.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.databinding.ListItemBinding
import com.stockbit.model.DataItem

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

        fun bindItem(item: DataItem) {
            binding.tvCryptoName.text = item.coinInfo?.name ?: ""
            binding.tvCryptoFullname.text = item.coinInfo?.fullName
            binding.tvCryptoValue.text = item.display?.usd?.price ?: ""
        }
    }


}