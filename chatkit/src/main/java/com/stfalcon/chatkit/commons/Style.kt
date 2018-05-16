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

package com.stfalcon.chatkit.commons

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.support.annotation.AttrRes
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue

import com.stfalcon.chatkit.R

/**
 * Base class for chat component styles
 */
abstract class Style protected constructor(protected var context: Context, protected var attrs: AttributeSet) {
    protected var resources: Resources = context.resources

    protected val systemAccentColor: Int
        get() = getSystemColor(R.attr.colorAccent)

    protected val systemPrimaryColor: Int
        get() = getSystemColor(R.attr.colorPrimary)

    protected val systemPrimaryDarkColor: Int
        get() = getSystemColor(R.attr.colorPrimaryDark)

    protected val systemPrimaryTextColor: Int
        get() = getSystemColor(android.R.attr.textColorPrimary)

    protected val systemHintColor: Int
        get() = getSystemColor(android.R.attr.textColorHint)

    private fun getSystemColor(@AttrRes attr: Int): Int {
        val typedValue = TypedValue()

        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(attr))
        val color = a.getColor(0, 0)
        a.recycle()

        return color
    }

    protected fun getDimension(@DimenRes dimen: Int): Int {
        return resources.getDimensionPixelSize(dimen)
    }

    protected fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    protected fun getDrawable(@DrawableRes drawable: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawable)
    }

    protected fun getVectorDrawable(@DrawableRes drawable: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawable)
    }

}
