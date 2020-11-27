package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.mohammadsianaki.core.databinding.ItemSectionBinding
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.presentation.model.ViewTypes
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSectionViewHolder
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceCategoryItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServicesSectionViewHolder

class HomeAdapter : BaseRecyclerAdapter<RecyclerData>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerData> = when (viewType) {
        ViewTypes.SERVICES.value -> ServicesSectionViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).also {
            it.bindData(ServiceSection("Services", items.filterIsInstance<ServiceCategoryItemModel>()))
        }
        ViewTypes.PROMO.value -> PromoSectionViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).also {
            it.bindData(PromoSection("Promotions", items.filterIsInstance<PromoItemModel>()))
        }
        else -> throw  IllegalStateException("")
    } as BaseViewHolder<RecyclerData>
}