package com.github.mohammadsianaki.tavansazan.data

import com.github.mohammadsianaki.tavansazan.data.api.detail.RemoteServiceDetailEntity
import com.github.mohammadsianaki.tavansazan.data.api.detail.ServiceDetailResponseDto
import com.github.mohammadsianaki.tavansazan.data.api.home.HomeResponseDto
import com.github.mohammadsianaki.tavansazan.data.api.home.RemoteCategoryEntity
import com.github.mohammadsianaki.tavansazan.data.api.home.RemotePromotionEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.*


fun HomeResponseDto.toHomePageEntity() = HomePageEntity(
    HomePageHeaderEntity(title, subTitle),
    categories.map { it.toHomePageServiceEntity() },
    promotions.map { it.toHomePagePromoEntity() }
)

fun RemoteCategoryEntity.toHomePageServiceEntity() = HomePageServiceEntity(
    hasNewBadge, id, slug, title, subTitle, description, shortDescription, slogan, image.originalUrl
)

fun RemotePromotionEntity.toHomePagePromoEntity() = HomePagePromoEntity(
    image.originalUrl
)

fun ServiceDetailResponseDto.toServiceDetailEntity() = ServiceDetailEntity(
    slug,
    title,
    description,
    image.originalUrl,
    payload.map { it.toServiceDetailPurchasePlansEntity() }
)

fun RemoteServiceDetailEntity.toServiceDetailPurchasePlansEntity() =
    ServiceDetailPurchasePlansEntity(
        title,
        subTitle,
        shortDescription,
        isActive,
        isSpecial,
        slug,
        basePrice,
        listBasePrice,
        hasDiscount,
        discountPercentage,
        image.originalUrl
    )