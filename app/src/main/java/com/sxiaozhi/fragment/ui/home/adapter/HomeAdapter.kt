package com.sxiaozhi.fragment.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sxiaozhi.fragment.core.base.adapter.BaseAdapter
import com.sxiaozhi.fragment.data.CaseBean

class HomeAdapter(private val callback: Callback) :
    BaseAdapter<RecyclerView.ViewHolder, CaseBean>() {

    interface Callback {
        fun onClickItem(item: CaseBean)

        fun onClickAsk(item: CaseBean)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return HomeViewHolder(parent.context, parent, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bindData(getItemData(position))
            }
        }
    }
}
