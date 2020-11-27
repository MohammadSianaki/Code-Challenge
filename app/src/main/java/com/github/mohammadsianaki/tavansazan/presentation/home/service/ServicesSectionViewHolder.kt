package com.github.mohammadsianaki.tavansazan.presentation.home.service

import com.github.mohammadsianaki.core.databinding.ItemSectionBinding
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.SectionOrientation
import com.github.mohammadsianaki.core.ui.adapter.SectionRecyclerData
import com.github.mohammadsianaki.core.ui.adapter.SectionViewHolder
import com.github.mohammadsianaki.tavansazan.presentation.ViewTypes

class ServicesSectionViewHolder(
    binding: ItemSectionBinding
) : SectionViewHolder<ServiceSection, ServiceCategoryItemModel>(binding) {
    override val adapter: BaseRecyclerAdapter<ServiceCategoryItemModel> = ServiceAdapter()
}

data class ServiceSection(
    private val sectionLabel: String,
    private val services: List<ServiceCategoryItemModel>
) : SectionRecyclerData<ServiceCategoryItemModel> {
    override val items: List<ServiceCategoryItemModel> = services
    override val sectionTitle: String = sectionLabel
    override val orientation: SectionOrientation = SectionOrientation.GRID_VERTICAL
    override val viewType: Int = ViewTypes.SERVICES.value
}