package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceCategoryItemModel

data class HomePageItemModel(
    val header: HomePageHeaderItemModel,
    val services: List<ServiceCategoryItemModel>,
    val promos: List<PromoItemModel>
) : RecyclerData {
    override val viewType: Int
        get() = TODO("Not yet implemented")
}


data class HomePageHeaderItemModel(
    val title: String,
    val subtitle: String
)