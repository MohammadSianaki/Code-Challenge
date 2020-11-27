package com.github.mohammadsianaki.tavansazan.presentation.home.service

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.presentation.ViewTypes

data class ServiceCategoryItemModel(
    val hasNewBadge: String,
    val id: String,
    val slug: String,
    val title: String,
    val subTitle: String,
    val description: String,
    val shortDescriptions: String,
    val slogan: String,
    val imageUrl: String

) : RecyclerData {
    override val viewType: Int = ViewTypes.SERVICES.value
}