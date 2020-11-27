package com.github.mohammadsianaki.tavansazan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.tavansazan.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : RecyclerFragment<FragmentHomeBinding, TestData, HomeViewModel>() {
    override val recyclerAdapter: HomeAdapter by lazy(LazyThreadSafetyMode.NONE) { HomeAdapter() }
    override val viewModel: HomeViewModel by viewModels()

    override fun createViewBinding(container: ViewGroup?): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(
            LayoutInflater.from(container?.context), container, false
        )
    }

    override fun loadData() {
        viewModel.loadData()
    }

    override fun onNetworkStateChanged(networkState: NetworkState) {
        TODO("Not yet implemented")
    }

}