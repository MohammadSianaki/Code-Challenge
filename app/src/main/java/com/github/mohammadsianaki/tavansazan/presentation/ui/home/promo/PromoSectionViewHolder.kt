package com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo

import com.github.mohammadsianaki.core.databinding.ItemSectionBinding
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.SectionOrientation
import com.github.mohammadsianaki.core.ui.adapter.SectionRecyclerData
import com.github.mohammadsianaki.core.ui.adapter.SectionViewHolder

class PromoSectionViewHolder(
    binding: ItemSectionBinding
) : SectionViewHolder<PromoSection, PromoItemModel>(binding) {
    override val adapter: BaseRecyclerAdapter<PromoItemModel> = PromoAdapter()
}

data class PromoSection(
    private val sectionLabel: String,
    private val promos: List<PromoItemModel>
) : SectionRecyclerData<PromoItemModel> {
    override val items: List<PromoItemModel> = promos
    override val sectionTitle: String = sectionLabel
    override val orientation: SectionOrientation = SectionOrientation.HORIZONTAL
    override val viewType: Int = ViewTypes.PROMO.value
}