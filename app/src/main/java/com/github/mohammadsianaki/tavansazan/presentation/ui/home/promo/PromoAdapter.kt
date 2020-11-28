package com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.BaseViewHolder
import com.github.mohammadsianaki.tavansazan.databinding.ItemPromoBinding

class PromoAdapter : BaseRecyclerAdapter<PromoItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PromoItemModel> = PromoViewHolder(
        ItemPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClickListener
    )

    override fun onViewRecycled(holder: BaseViewHolder<PromoItemModel>) {
        check(holder is PromoViewHolder)
        Glide.with(holder.itemView.context).clear(holder.binding.promoImage)
    }
}


class PromoViewHolder(
    val binding: ItemPromoBinding,
    private val onItemClickListener: (item: PromoItemModel, position: Int) -> Unit
) : BaseViewHolder<PromoItemModel>(binding.root) {
    override fun bindData(item: PromoItemModel) {
        binding.root.setOnClickListener { onItemClickListener(item, adapterPosition) }
        with(binding) {
            Glide.with(promoImage.context).load(item.imageUrl).into(promoImage)
        }
    }
}