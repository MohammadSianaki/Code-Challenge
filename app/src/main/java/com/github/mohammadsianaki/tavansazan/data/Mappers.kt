package com.github.mohammadsianaki.tavansazan.data

import com.github.mohammadsianaki.tavansazan.data.api.home.HomeResponseDto
import com.github.mohammadsianaki.tavansazan.data.api.home.RemoteCategoryEntity
import com.github.mohammadsianaki.tavansazan.data.api.home.RemotePromotionEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageHeaderEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePagePromoEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageServiceEntity


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