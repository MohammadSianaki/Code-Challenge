package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

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
)