package com.sxiaozhi.somking.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sxiaozhi.somking.core.extensions.loadRoundAvatar
import com.sxiaozhi.somking.data.AgentBean
import com.sxiaozhi.somking.databinding.ViewAgentItemBinding

class AgentViewHolder(
    private val binding: ViewAgentItemBinding,
    private val callback: AgentAdapter.Callback
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        context: Context,
        parent: ViewGroup,
        callback: AgentAdapter.Callback
    ) : this(
        ViewAgentItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        ),
        callback
    )

    fun bindData(item: AgentBean) {
        with(binding) {
            val context = root.context
            titleLabel.isVisible = bindingAdapterPosition == 0

            avatar.loadRoundAvatar(context, item.avatar)
            name.text = item.name
            info.text = item.comment
            nameTip.text = item.tag
            certificationLogo.isVisible = item.idVerified == "1"

            action.setOnClickListener {
                callback.onClickAsk(item)
            }
        }
    }
}
