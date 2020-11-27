package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.tavansazan.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment : RecyclerFragment<FragmentHomeBinding, HomePageItemModel, HomeViewModel>() {
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

    override fun handleSuccessState(resource: Resource<List<HomePageItemModel>>?) {
        checkNotNull(resource)
        super.handleSuccessState(resource)
        with(resource.data!![0].header) {
            viewBinding.homeTitle.text = title
            viewBinding.homeCaption.text = subtitle
        }
    }

    override fun onNetworkStateChanged(networkState: NetworkState) {
        Delegates.observable(networkState) { _, oldValue, newValue ->
            if (oldValue == NetworkState.DISCONNECTED && newValue == NetworkState.CONNECTED) {
                loadData()
            }
        }
    }

}