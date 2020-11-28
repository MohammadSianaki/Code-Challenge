package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData

data class ServiceDetailItemModel(
    val slug: String,
    val title: String,
    val descriptions: String,
    val imageUrl: String,
    val purchasePlans: List<ServiceDetailPurchasePlansItemModel>
)

data class ServiceDetailPurchasePlansItemModel(
    val title: String,
    val subTitle: String,
    val shortDescription: String,
    val isActive: Boolean,
    val isSpecial: Boolean,
    val slug: String,
    val basePrice: Long,
    val listBasePrice: Long,
    val hasDiscount: Boolean,
    val discountPercentage: Long,
    val imageUrl: String,
) : RecyclerData {
    override val viewType: Int =
        com.github.mohammadsianaki.tavansazan.presentation.model.ViewTypes.PURCHASE_PLAN.value
}