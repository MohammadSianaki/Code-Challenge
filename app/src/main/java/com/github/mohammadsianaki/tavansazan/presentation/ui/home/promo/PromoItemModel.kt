package com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData

data class PromoItemModel(
    val imageUrl: String
) : RecyclerData {
    override val viewType: Int =
        com.github.mohammadsianaki.tavansazan.presentation.model.ViewTypes.PROMO.value
}