package com.sxiaozhi.somking.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sxiaozhi.somking.core.base.adapter.BaseAdapter
import com.sxiaozhi.somking.data.AgentBean

class AgentAdapter(private val callback: Callback) :
    BaseAdapter<RecyclerView.ViewHolder, AgentBean>() {

    interface Callback {
        fun onClickAsk(item: AgentBean)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return AgentViewHolder(parent.context, parent, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AgentViewHolder -> {
                holder.bindData(getItemData(position))
            }
        }
    }
}
