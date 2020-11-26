package com.github.mohammadsianaki.tavansazan.data.api.home

import com.google.gson.annotations.SerializedName

data class HomeResponseDto(
    val categories: List<RemoteCategoryEntity>,
    val onGoingServiceRequests: List<Any>,
    val promotions: List<RemotePromotionEntity>,
    val subTitle: String,
    val title: String
)

data class RemoteCategoryEntity(
    val _id: String,
    val bookingFormPrimaryActionLabel: String,
    val description: String,
    val descriptions: Descriptions,
    val displayType: String,
    val hasNewBadge: Boolean,
    val id: String,
    val image: Image,
    val isActive: Boolean,
    val searchPlaceholder: String,
    val serviceDetailsPrimaryActionLabel: String,
    val shortDescription: String,
    val shortDescriptions: ShortDescriptions,
    val skipButtonLabel: String,
    val skipHint: String,
    val slogan: String,
    val slogans: Slogans,
    val slug: String,
    val sort: Int,
    val subTitle: String,
    val subTitles: SubTitles,
    val title: String,
    val titles: Titles
)

data class RemotePromotionEntity(
    val image: ImageX
)

data class Descriptions(
    val ar: String,
    val en: String
)

data class Image(
    val originalUrl: String,
    @SerializedName("originalUrl@2x") val originalUrl2x: String,
    @SerializedName("originalUrl@2x") val originalUrl3x: String,
    @SerializedName("originalUrl@2x") val originalUrl4x: String,
    val originalUrlPDF: String,
    val originalUrlSVG: String
)

data class ShortDescriptions(
    val ar: String,
    val en: String
)

data class Slogans(
    val ar: String,
    val en: String
)

data class SubTitles(
    val ar: String,
    val en: String
)

data class Titles(
    val ar: String,
    val en: String
)

data class ImageX(
    val originalUrl: String
)