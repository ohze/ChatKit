/*******************************************************************************
 * Copyright 2016 stfalcon.com
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stfalcon.chatkit.messages

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

internal class RecyclerScrollMoreListener(layoutManager: LinearLayoutManager, private val loadMoreListener: OnLoadMoreListener?) : RecyclerView.OnScrollListener() {
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true

    private val mLayoutManager: RecyclerView.LayoutManager

    init {
        this.mLayoutManager = layoutManager
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView?, dx: Int, dy: Int) {
        if (loadMoreListener != null) {
            var lastVisibleItemPosition = 0
            val totalItemCount = mLayoutManager.itemCount

            if (mLayoutManager is StaggeredGridLayoutManager) {
                val lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            } else if (mLayoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            } else if (mLayoutManager is GridLayoutManager) {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            }

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = 0
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    this.loading = true
                }
            }

            if (loading && totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
            }

            val visibleThreshold = 5
            if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                currentPage++
                loadMoreListener.onLoadMore(currentPage, totalItemCount)
                loading = true
            }
        }
    }

    internal interface OnLoadMoreListener {
        fun onLoadMore(page: Int, total: Int)
    }
}
