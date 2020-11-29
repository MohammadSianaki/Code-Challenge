package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.tavansazan.databinding.FragmentServiceDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ServiceDetailFragment :
    RecyclerFragment<FragmentServiceDetailBinding, ServiceDetailPurchasePlansItemModel, ServiceDetailViewModel, ServiceDetailFragmentArgs>() {
    override val viewModel: ServiceDetailViewModel by viewModels()
    override val recyclerAdapter: ServicePurchasePlansAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ServicePurchasePlansAdapter()
    }
    override val recyclerViewLayoutManager: RecyclerView.LayoutManager by lazy {
        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
    }
    override val navArgs: ServiceDetailFragmentArgs by navArgs()

    override fun loadData() {
        viewModel.loadData(navArgs)
    }

    override fun initUI(rootView: View) {
        super.initUI(rootView)
        viewBinding.toolbar.backButton.setOnClickListener { findNavController().popBackStack() }
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