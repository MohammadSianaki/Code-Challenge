package com.github.mohammadsianaki.tavansazan.presentation.home.promo

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import  com.github.mohammadsianaki.tavansazan.presentation.ViewTypes

data class PromoItemModel(
    val imageUrl: String
) : RecyclerData {
    override val viewType: Int = ViewTypes.PROMO.value
}