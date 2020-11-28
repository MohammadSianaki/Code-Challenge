package com.github.mohammadsianaki.tavansazan.domain.entity

data class ServiceDetailEntity(
    val slug: String,
    val title: String,
    val descriptions: String,
    val imageUrl: String,
    val purchasePlans: List<ServiceDetailPurchasePlansEntity>
)

data class ServiceDetailPurchasePlansEntity(
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