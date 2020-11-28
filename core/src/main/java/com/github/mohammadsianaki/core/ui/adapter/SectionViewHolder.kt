package com.github.mohammadsianaki.core.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohammadsianaki.core.databinding.ItemSectionBinding

abstract class SectionViewHolder<Section, SectionItem>(
    private val binding: ItemSectionBinding,
    private val clickHandler: SectionViewHolderClickHandler?
) : BaseViewHolder<Section>(binding.root)
        where  Section : SectionRecyclerData<SectionItem>, SectionItem : RecyclerData {

    abstract val adapter: BaseRecyclerAdapter<SectionItem>

    interface SectionViewHolderClickHandler {
        fun <SectionItem> onItemClicked(item: SectionItem, position: Int)
    }


    override fun bindData(section: Section) {
        binding.sectionLabel.text = section.sectionTitle
        clickHandler?.let {
            adapter.onItemClickListener = it::onItemClicked
        }
        binding.sectionRecyclerView.apply {
            adapter = this@SectionViewHolder.adapter.apply {
                items = section.items.toMutableList()
            }
            layoutManager = when (section.orientation.value) {
                SectionOrientation.HORIZONTAL.value -> LinearLayoutManager(
                    itemView.context,
                    RecyclerView.HORIZONTAL,
                    false
                )
                SectionOrientation.VERTICAL.value -> LinearLayoutManager(
                    itemView.context,
                    RecyclerView.VERTICAL,
                    false
                )
                SectionOrientation.GRID_HORIZONTAL.value -> {
                    section as GridSectionRecyclerData<*>
                    GridLayoutManager(
                        itemView.context,
                        section.spanCount,
                        RecyclerView.HORIZONTAL,
                        false
                    )
                }
                SectionOrientation.GRID_VERTICAL.value -> {
                    section as GridSectionRecyclerData<*>
                    GridLayoutManager(
                        itemView.context,
                        section.spanCount,
                        RecyclerView.VERTICAL,
                        false
                    )
                }
                else -> throw IllegalStateException("invalid section orientation")
            }
        }
    }
}