package com.github.mohammadsianaki.tavansazan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.mohammadsianaki.core.databinding.ItemSectionBinding
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.databinding.ItemServiceBinding
import com.github.mohammadsianaki.tavansazan.presentation.ViewTypes
import com.github.mohammadsianaki.tavansazan.presentation.home.promo.PromoSectionViewHolder

class HomeAdapter : BaseRecyclerAdapter<RecyclerData>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerData> = when (viewType) {
        ViewTypes.SERVICES.value -> ServicesViewHolder(
            ItemServiceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        ViewTypes.PROMO.value -> PromoSectionViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        else -> throw  IllegalStateException("")
    } as BaseViewHolder<RecyclerData>
}