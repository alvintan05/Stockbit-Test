package com.stockbit.hiring.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.databinding.ItemLoadingStateBinding

class WatchlistLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<WatchlistLoadStateAdapter.WatchlistLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: WatchlistLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): WatchlistLoadStateViewHolder = WatchlistLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )

    inner class WatchlistLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.progressList.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState !is LoadState.Loading
            binding.tvError.isVisible = loadState !is LoadState.Loading
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }
    }
}