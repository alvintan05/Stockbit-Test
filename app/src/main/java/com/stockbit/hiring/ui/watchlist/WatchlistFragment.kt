package com.stockbit.hiring.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stockbit.hiring.databinding.FragmentWatchlistBinding
import com.stockbit.model.DataItem
import com.stockbit.repository.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var watchlistAdapter: WatchlistAdapter
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

        watchlistAdapter = WatchlistAdapter()
        binding.rvCrypto.adapter = watchlistAdapter
        binding.rvCrypto.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )

        watchlistViewModel.data.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> binding.swipeRefresh.isRefreshing = true
                Resource.Status.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    showData(resource?.data)
                }
                Resource.Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    showError()
                }
            }

        })

        binding.swipeRefresh.setOnRefreshListener { watchlistViewModel.getData() }
    }


    private fun showData(data: List<DataItem?>?) {
        watchlistAdapter.setList(data)
        binding.rvCrypto.visibility = View.VISIBLE
        binding.ivWarning.visibility = View.GONE
    }

    private fun showError() {
        binding.ivWarning.visibility = View.VISIBLE
        binding.rvCrypto.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}