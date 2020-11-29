package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.github.mohammadsianaki.core.extensions.gone
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.tavansazan.databinding.ItemPurchasePlanBinding
import com.github.mohammadsianaki.tavansazan.presentation.utils.Colors

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
            if (item.isSpecial) {
                root.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        Colors.dark_blue
                    )
                )
            }
            if (item.hasDiscount) {
                badge.text = item.discountPercentage.toHumanReadablePercent()
            }else {
                badge.gone()
            }
        }
    }
}

private fun Long.toHumanReadablePercent() = "%$this"