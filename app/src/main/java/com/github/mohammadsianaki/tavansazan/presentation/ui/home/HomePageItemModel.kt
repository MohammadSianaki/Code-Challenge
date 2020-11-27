package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceSection

data class HomePageItemModel(
    val header: HomePageHeaderItemModel,
    val services: ServiceSection,
    val promos: PromoSection
) {
    val items: List<RecyclerData> = mutableListOf<RecyclerData>().apply {
        add(services)
        add(promos)
    }
}


data class HomePageHeaderItemModel(
    val title: String,
    val subtitle: String
)