package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.tavansazan.databinding.FragmentServiceDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ServiceDetailFragment :
    RecyclerFragment<FragmentServiceDetailBinding, ServiceDetailPurchasePlansItemModel, ServiceDetailViewModel>() {
    override val viewModel: ServiceDetailViewModel by viewModels()
    override val recyclerAdapter: ServicePurchasePlansAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ServicePurchasePlansAdapter()
    }
    override val recyclerViewLayoutManager: RecyclerView.LayoutManager =
        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)

    override fun loadData() {
        viewModel.loadData()
    }

    override fun createViewBinding(container: ViewGroup?): FragmentServiceDetailBinding? {
        return FragmentServiceDetailBinding.inflate(
            LayoutInflater.from(container?.context), container, false
        )
    }

    override fun onNetworkStateChanged(networkState: NetworkState) {
        Delegates.observable(networkState) { _, oldValue, newValue ->
            if (oldValue == NetworkState.DISCONNECTED && newValue == NetworkState.CONNECTED) {
                loadData()
            }
        }
    }
}