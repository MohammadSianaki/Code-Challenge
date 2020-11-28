package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.tavansazan.databinding.ItemPurchasePlanBinding

class ServicePurchasePlansAdapter : BaseRecyclerAdapter<ServiceDetailPurchasePlansItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ServiceDetailPurchasePlansItemModel> = PurchasePlanViewHolder(
        ItemPurchasePlanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )
}

class PurchasePlanViewHolder(
    private val binding: ItemPurchasePlanBinding
) : BaseViewHolder<ServiceDetailPurchasePlansItemModel>(binding.root) {
    override fun bindData(item: ServiceDetailPurchasePlansItemModel) {
        with(binding) {
            Glide.with(serviceLogo.context).load(item.imageUrl).into(serviceLogo)
            serviceTitle.text = item.title
            serviceSubTitle.text = item.subTitle
            serviceDescription.text = item.shortDescription
        }
    }
}