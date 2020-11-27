package com.github.mohammadsianaki.tavansazan.presentation.model

import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageHeaderEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePagePromoEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageServiceEntity
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.HomePageHeaderItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.HomePageItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceCategoryItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceSection

fun HomePageEntity.toHomePageItemModel() = HomePageItemModel(
    header.toHeaderItemModel(),
    ServiceSection("Services", services.map { it.toServiceItemModel() }),
    PromoSection("Promotions", promotions.map { it.toPromoItemModel() })
)

fun HomePageHeaderEntity.toHeaderItemModel() = HomePageHeaderItemModel(title, subTitles)

fun HomePageServiceEntity.toServiceItemModel() = ServiceCategoryItemModel(
    hasNewBadge,
    id,
    slug,
    title,
    subTitle,
    description,
    shortDescriptions,
    slogan,
    imageUrl
)

fun HomePagePromoEntity.toPromoItemModel() = PromoItemModel(imageUrl)

