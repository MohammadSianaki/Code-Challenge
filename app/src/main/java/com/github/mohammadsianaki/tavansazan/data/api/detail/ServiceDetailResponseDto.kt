package com.github.mohammadsianaki.tavansazan.data.api.detail

import com.google.gson.annotations.SerializedName

data class ServiceDetailResponseDto(
    val checkout: Checkout,
    @SerializedName("data") val payload: List<RemoteServiceDetailEntity>,
    val description: String,
    val image: RemoteImageQualitiesEntity,
    val slogan: String,
    val slug: String,
    val title: String
)

data class Checkout(
    val _id: String,
    val addAnoterServiceTitle: String,
    val addAnoterServiceTitles: AddAnoterServiceTitles,
    val extraNoteField: ExtraNoteField,
    val id: String,
    val maxNumOfServices: Int,
    val minOrderTotal: Int,
    val paymentMethods: List<RemotePaymentMethodEntity>,
    val primaryActionLabel: String,
    val primaryActionLabels: PrimaryActionLabels,
    val scheduling: Scheduling,
    val skipHint: String
)

data class RemoteServiceDetailEntity(
    val _id: String,
    val basePrice: Int,
    val categoryId: String,
    val coverImage: RemoteCoverImageEntity,
    val coverImageId: String,
    val description: String,
    val discountPercentage: Int,
    val hasDiscount: Boolean,
    val id: String,
    val image: RemoteImageQualitiesEntity,
    val isActive: Boolean,
    val isSpecial: Boolean,
    val listBasePrice: Int,
    val selectItemsModalDescription: String,
    val shortDescription: String,
    val shortDescriptions: RemoteShortDescriptionsEntity,
    val slug: String,
    val sort: Int,
    val subTitle: String,
    val subTitles: RemoteSubTitlesEntity,
    val title: String,
    val titles: RemoteTitlesEntity
)

data class AddAnoterServiceTitles(
    val ar: String,
    val en: String
)

data class ExtraNoteField(
    val _id: String,
    val id: String,
    val isEnable: Boolean,
    val placeholder: String,
    val title: String
)

data class RemotePaymentMethodEntity(
    val id: Any,
    val image: RemoteImageQualitiesEntity,
    val isDefault: Boolean,
    val slug: String,
    val title: String,
    val titles: RemoteTitlesEntity
)

data class PrimaryActionLabels(
    val ar: String,
    val en: String
)

data class Scheduling(
    val _id: String,
    val hasReturnDate: Boolean,
    val id: String,
    val returnDateTitle: String,
    val scheduleTitle: String,
    val scheduleTitles: RemoteScheduleTitlesEntity
)


data class RemoteCoverImageEntity(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val fileName: String,
    val id: String,
    val originalUrl: String,
    val path: String,
    val serviceId: String,
    val thumbnails: RemoteThumbnailsEntity,
    val type: String,
    val updatedAt: String
)


data class RemoteThumbnailsEntity(
    val has128x128: Boolean,
    val has256x256: Boolean,
    val has512x512: Boolean
)

data class RemoteImageQualitiesEntity(
    val originalUrl: String,
    @SerializedName("originalUrl@2x") val originalUrl2x: String,
    @SerializedName("originalUrl@3x") val originalUrl3x: String,
    @SerializedName("originalUrl@4x") val originalUrl4x: String,
    val originalUrlPDF: String,
    val originalUrlSVG: String
)

data class RemoteShortDescriptionsEntity(
    val ar: String,
    val en: String
)

data class RemoteSubTitlesEntity(
    val ar: String,
    val en: String
)

data class RemoteTitlesEntity(
    val ar: String,
    val en: String
)

data class RemoteScheduleTitlesEntity(
    val ar: String,
    val en: String
)