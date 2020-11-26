package com.github.mohammadsianaki.core.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ItemType : RecyclerData> :
    RecyclerView.Adapter<BaseViewHolder<ItemType>>() {
    var onItemClickListener: (item: ItemType, position: Int) -> Unit = { item, position -> }
    var items: MutableList<ItemType> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemType>, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = items[position].viewType
}
