package com.github.mohammadsianaki.tavansazan.presentation.home.service

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.tavansazan.databinding.ItemServiceBinding

class ServiceAdapter : BaseRecyclerAdapter<ServiceCategoryItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ServiceCategoryItemModel> = ServiceViewHolder(
        ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}

class ServiceViewHolder(
    private val binding: ItemServiceBinding
) : BaseViewHolder<ServiceCategoryItemModel>(binding.root) {
    override fun bindData(item: ServiceCategoryItemModel) {
        with(binding) {

        }
    }
}