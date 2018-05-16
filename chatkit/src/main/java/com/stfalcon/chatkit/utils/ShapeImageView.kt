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

package com.stfalcon.chatkit.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView

/**
 * ImageView with mask what described with Bézier Curves
 */

class ShapeImageView : ImageView {
    private var path: Path? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path = Path()
        val halfWidth = w.toFloat() / 2f
        val firstParam = w.toFloat() * 0.1f
        val secondParam = w.toFloat() * 0.8875f

        //Bézier Curves
        path!!.moveTo(halfWidth, w.toFloat())
        path!!.cubicTo(firstParam, w.toFloat(), 0f, secondParam, 0f, halfWidth)
        path!!.cubicTo(0f, firstParam, firstParam, 0f, halfWidth, 0f)
        path!!.cubicTo(secondParam, 0f, w.toFloat(), firstParam, w.toFloat(), halfWidth)
        path!!.cubicTo(w.toFloat(), secondParam, secondParam, w.toFloat(), halfWidth, w.toFloat())
        path!!.close()
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            canvas.clipPath(path!!)
            super.onDraw(canvas)
        }
    }
}
