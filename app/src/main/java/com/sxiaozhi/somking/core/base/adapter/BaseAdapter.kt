package com.sxiaozhi.somking.core.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, M> :
    RecyclerView.Adapter<VH>() {

    private val recyclerData: MutableList<M> = mutableListOf()

    var recyclerView: RecyclerView? = null

    private var listener: EndlessScrollListener? = null

    var onLoadMore: ((reset: Boolean) -> Unit)? = null

    var cursor: Cursor? = null

    val isLoading: Boolean
        get() {
            return listener?.isLoading() ?: false
        }

    val afterCursor: Int?
        get() = cursor?.afterCursor

    fun noMoreData(): Boolean {
        return cursor != null && cursor?.afterCursor == null
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        this.listener =
            object :
                EndlessScrollListener(recyclerView.layoutManager as RecyclerView.LayoutManager) {
                override fun noMore(): Boolean {
                    return noMoreData()
                }

                override fun onLoadMore() {
                    onLoadMore?.invoke(cursor == null)
                }
            }.apply {
                recyclerView.addOnScrollListener(this)
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun startLoadData() {
        listener?.onStartLoading()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun endLoadData() {
        notifyDataSetChanged()
        listener?.onEndLoading()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
    }

    override fun getItemCount(): Int {
        return recyclerData.size
    }

    fun getItemData(position: Int): M {
        return recyclerData[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setRecyclerData(data: List<M>) {
        recyclerData.clear()
        recyclerData.addAll(data)
        notifyDataSetChanged()
    }

    fun insertRecyclerData(index: Int, data: List<M>) {
        recyclerData.addAll(index, data)
        notifyItemRangeInserted(index, data.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addRecyclerData(data: List<M>, cursor: Cursor? = null, reset: Boolean = false) {
        this.cursor = cursor
        listener?.onEndLoading()
        if (reset) {
            recyclerData.clear()
        }
        recyclerData.addAll(data)
        notifyDataSetChanged()
    }

    fun insertRecyclerData(data: List<M>, cursor: Cursor? = null, reset: Boolean = false) {
        this.cursor = cursor
        listener?.onEndLoading()
        if (reset) {
            recyclerData.clear()
        }
        val insertIndex = recyclerData.size
        recyclerData.addAll(data)
        notifyItemRangeInserted(insertIndex, data.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        cursor = null
        recyclerData.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<M> {
        return recyclerData
    }

    fun removeData(index: Int) {
        recyclerData.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateData(index: Int, data: M) {
        recyclerData[index] = data
        notifyItemChanged(index)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeData(data: List<M>) {
        recyclerData.removeAll(data)
        notifyDataSetChanged()
    }

    fun removeData(start: Int, end: Int) {
        recyclerData.subList(start, end).clear()
        notifyItemRangeRemoved(start, end - start)
    }

    fun addData(index: Int, data: M) {
        recyclerData.add(index, data)
        notifyItemInserted(index)
    }

    fun onDataMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(recyclerData, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}
