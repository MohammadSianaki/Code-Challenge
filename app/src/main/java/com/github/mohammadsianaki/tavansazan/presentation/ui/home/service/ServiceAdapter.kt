package com.github.mohammadsianaki.tavansazan.presentation.ui.home.service

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.mohammadsianaki.core.extensions.gone
import com.github.mohammadsianaki.core.extensions.visible
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.tavansazan.databinding.ItemServiceBinding

class ServiceAdapter : BaseRecyclerAdapter<ServiceCategoryItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ServiceCategoryItemModel> = ServiceViewHolder(
        ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClickListener
    )
}

class ServiceViewHolder(
    private val binding: ItemServiceBinding,
    private val onItemClickListener: (item: ServiceCategoryItemModel, position: Int) -> Unit
) : BaseViewHolder<ServiceCategoryItemModel>(binding.root) {
    override fun bindData(item: ServiceCategoryItemModel) {
        binding.root.setOnClickListener { onItemClickListener(item, adapterPosition) }
        with(binding) {
            if (true) badge.visible() else badge.gone()
            Glide.with(serviceLogo.context).load(item.imageUrl).into(serviceLogo)
            serviceTitle.text = item.title
            serviceSubTitle.text = item.subTitle
            serviceDescription.text = item.shortDescriptions
        }
    }
}