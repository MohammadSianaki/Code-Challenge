package com.github.mohammadsianaki.tavansazan.presentation.ui.home.service

import com.github.mohammadsianaki.core.databinding.ItemSectionBinding
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.SectionOrientation
import com.github.mohammadsianaki.core.ui.adapter.SectionRecyclerData
import com.github.mohammadsianaki.core.ui.adapter.SectionViewHolder

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
    override val viewType: Int = com.github.mohammadsianaki.tavansazan.presentation.model.ViewTypes.SERVICES.value
}