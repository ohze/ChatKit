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

package com.stfalcon.chatkit.dialogs

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

import com.stfalcon.chatkit.commons.models.IDialog

/**
 * Component for displaying list of dialogs
 */
class DialogsList : RecyclerView {

    private var dialogStyle: DialogListStyle? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        parseStyle(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        parseStyle(context, attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val animator = DefaultItemAnimator()

        layoutManager = layout
        itemAnimator = animator
    }

    /**
     * Don't use this method for setting your adapter, otherwise exception will by thrown.
     * Call [.setAdapter] instead.
     */
    override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        throw IllegalArgumentException("You can't set adapter to DialogsList. Use #setAdapter(DialogsListAdapter) instead.")
    }

    /**
     * Sets adapter for DialogsList
     *
     * @param adapter  Adapter. Must extend DialogsListAdapter
     * @param <DIALOG> Dialog model class
    </DIALOG> */
    fun <DIALOG : IDialog<*>> setAdapter(adapter: DialogsListAdapter<DIALOG>) {
        setAdapter(adapter, true)
    }

    /**
     * Sets adapter for DialogsList
     *
     * @param adapter       Adapter. Must extend DialogsListAdapter
     * @param reverseLayout weather to use reverse layout for layout manager.
     * @param <DIALOG>      Dialog model class
    </DIALOG> */
    fun <DIALOG : IDialog<*>> setAdapter(adapter: DialogsListAdapter<DIALOG>, reverseLayout: Boolean) {
        val itemAnimator = DefaultItemAnimator()
        itemAnimator.supportsChangeAnimations = false

        val layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, reverseLayout)

        setItemAnimator(itemAnimator)
        setLayoutManager(layoutManager)

        adapter.setStyle(dialogStyle)

        super.setAdapter(adapter)
    }

    private fun parseStyle(context: Context, attrs: AttributeSet?) {
        dialogStyle = DialogListStyle.parse(context, attrs!!)
    }
}
