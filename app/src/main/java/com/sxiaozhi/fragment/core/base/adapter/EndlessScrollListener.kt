package com.sxiaozhi.fragment.core.base.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener(private val layoutManager: RecyclerView.LayoutManager) :
    RecyclerView.OnScrollListener() {

    companion object {
        const val VISIBLE_THRESHOLD = 4
    }

    private var loading: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        when (layoutManager) {
            is LinearLayoutManager -> {
                if (dy >= 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                    if (!loading &&
                        firstVisibleItem >= 0 &&
                        firstVisibleItem + VISIBLE_THRESHOLD >= totalItemCount - visibleItemCount &&
                        !noMore()
                    ) {
                        onStartLoading()
                    }
                }
            }
        }
    }

    fun isLoading(): Boolean {
        return loading
    }

    fun onEndLoading() {
        loading = false
    }

    fun onStartLoading() {
        loading = true
        onLoadMore()
    }

    abstract fun noMore(): Boolean

    abstract fun onLoadMore()
}
