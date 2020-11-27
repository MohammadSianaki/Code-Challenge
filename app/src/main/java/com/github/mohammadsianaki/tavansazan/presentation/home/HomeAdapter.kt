package com.github.mohammadsianaki.tavansazan.presentation.home

import android.view.ViewGroup
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.presentation.ViewTypes

class HomeAdapter : BaseRecyclerAdapter<RecyclerData>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerData> = when (viewType) {
        ViewTypes.SERVICES.value -> ServicesViewHolder()
        ViewTypes.PROMO.value -> PromoViewHolder()
        else -> throw  IllegalStateException("")
    } as BaseViewHolder<RecyclerData>
}