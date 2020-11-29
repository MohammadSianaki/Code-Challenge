package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mohammadsianaki.core.extensions.observeInFragment
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.core.ui.adapter.SectionViewHolder
import com.github.mohammadsianaki.core.utils.None
import com.github.mohammadsianaki.tavansazan.databinding.FragmentHomeBinding
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceCategoryItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceSection
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment : RecyclerFragment<FragmentHomeBinding, RecyclerData, HomeViewModel,None>() {

    private fun sectionClickHandler(): SectionViewHolder.SectionViewHolderClickHandler =
        object : SectionViewHolder.SectionViewHolderClickHandler {
            override fun <SectionItem> onItemClicked(item: SectionItem, position: Int) {
                when (item) {
                    is ServiceCategoryItemModel -> viewModel.onServiceItemClicked(item)
                    is PromoItemModel -> viewModel.onPromoItemClicked(item)
                }
            }
        }

    override val recyclerAdapter: HomeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeAdapter().apply {
            sectionViewHolderClickHandler = sectionClickHandler()
        }
    }
    override val viewModel: HomeViewModel by viewModels()
    override val navArgs:None = None

    override fun createViewBinding(container: ViewGroup?): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(
            LayoutInflater.from(container?.context), container, false
        )
    }

    override fun loadData() {
        viewModel.loadData(navArgs)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeInFragment(viewModel.headerLiveData){
            viewBinding.homeTitle.text = it.title
            viewBinding.homeCaption.text = it.subtitle
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