package com.github.mohammadsianaki.tavansazan.domain.entity

data class HomePageEntity(
    val header: HomePageHeaderEntity,
    val services: List<HomePageServiceEntity>,
    val promotions: List<HomePagePromoEntity>
)

data class HomePageHeaderEntity(
    val title: String,
    val subTitles: String
)

data class HomePageServiceEntity(
    val hasNewBadge: Boolean,
    val id: String,
    val slug: String,
    val title: String,
    val subTitle: String,
    val description: String,
    val shortDescriptions: String,
    val slogan: String,
    val imageUrl: String
)

data class HomePagePromoEntity(
    val imageUrl: String
)