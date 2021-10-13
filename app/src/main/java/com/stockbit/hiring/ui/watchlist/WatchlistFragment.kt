package com.stockbit.hiring.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stockbit.hiring.adapter.LocalPagingAdapter
import com.stockbit.hiring.adapter.WatchlistLoadStateAdapter
import com.stockbit.hiring.adapter.WatchlistPagingAdapter
import com.stockbit.hiring.databinding.FragmentWatchlistBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    //private lateinit var watchlistPagingAdapter: WatchlistPagingAdapter
    private lateinit var adapter: LocalPagingAdapter
    private val watchlistViewModel: WatchlistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LocalPagingAdapter()
        binding.rvCrypto.adapter = adapter.withLoadStateHeaderAndFooter(
            header = WatchlistLoadStateAdapter { adapter.retry() },
            footer = WatchlistLoadStateAdapter { adapter.retry() }
        )
        binding.rvCrypto.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                binding.rvCrypto.isVisible = it.refresh is LoadState.NotLoading
                binding.ivWarning.isVisible = it.refresh is LoadState.Error
            }
        }

        watchlistViewModel.data.observe(viewLifecycleOwner, { resource ->
            adapter.submitData(viewLifecycleOwner.lifecycle, resource)
        })

        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}