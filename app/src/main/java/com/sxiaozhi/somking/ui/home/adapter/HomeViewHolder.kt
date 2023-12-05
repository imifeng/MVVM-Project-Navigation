package com.sxiaozhi.somking.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sxiaozhi.somking.core.extensions.loadRoundAvatar
import com.sxiaozhi.somking.data.CaseBean
import com.sxiaozhi.somking.databinding.ViewHomeCaseItemBinding

class HomeViewHolder(
    private val binding: ViewHomeCaseItemBinding,
    private val callback: HomeAdapter.Callback
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        context: Context,
        parent: ViewGroup,
        callback: HomeAdapter.Callback
    ) : this(
        ViewHomeCaseItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        ),
        callback
    )

    fun bindData(item: CaseBean) {
        with(binding) {
            val context = root.context
            titleLabel.isVisible = bindingAdapterPosition == 0

            avatar.loadRoundAvatar(context, item.avatar)
            name.text = item.name
            info.text = "逾期类型：${item.type}"
            platform.text = item.platform
            amount.text = item.amount
            result.text = item.result

            action.setOnClickListener {
                callback.onClickAsk(item)
            }

            root.setOnClickListener {
                callback.onClickAsk(item)
            }
        }
    }
}
