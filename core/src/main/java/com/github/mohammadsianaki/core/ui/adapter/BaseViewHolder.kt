package com.github.mohammadsianaki.core.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in Item : RecyclerData>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(item: Item)
}
