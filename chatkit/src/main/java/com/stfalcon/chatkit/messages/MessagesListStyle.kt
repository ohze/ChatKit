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

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet

import com.stfalcon.chatkit.R
import com.stfalcon.chatkit.commons.Style

/**
 * Style for MessagesListStyle customization by xml attributes
 */
internal class MessagesListStyle private constructor(context: Context, attrs: AttributeSet) : Style(context, attrs) {

    var textAutoLinkMask: Int = 0
        private set
    var incomingTextLinkColor: Int = 0
        private set
    var outcomingTextLinkColor: Int = 0
        private set

    var incomingAvatarWidth: Int = 0
        private set
    var incomingAvatarHeight: Int = 0
        private set

    private var incomingBubbleDrawable: Int = 0
    private var incomingDefaultBubbleColor: Int = 0
    private var incomingDefaultBubblePressedColor: Int = 0
    private var incomingDefaultBubbleSelectedColor: Int = 0

    private var incomingImageOverlayDrawable: Int = 0
    private var incomingDefaultImageOverlayPressedColor: Int = 0
    private var incomingDefaultImageOverlaySelectedColor: Int = 0

    var incomingDefaultBubblePaddingLeft: Int = 0
        private set
    var incomingDefaultBubblePaddingRight: Int = 0
        private set
    var incomingDefaultBubblePaddingTop: Int = 0
        private set
    var incomingDefaultBubblePaddingBottom: Int = 0
        private set

    var incomingTextColor: Int = 0
        private set
    var incomingTextSize: Int = 0
        private set
    var incomingTextStyle: Int = 0
        private set

    var incomingTimeTextColor: Int = 0
        private set
    var incomingTimeTextSize: Int = 0
        private set
    var incomingTimeTextStyle: Int = 0
        private set

    var incomingImageTimeTextColor: Int = 0
        private set
    var incomingImageTimeTextSize: Int = 0
        private set
    var incomingImageTimeTextStyle: Int = 0
        private set

    private var outcomingBubbleDrawable: Int = 0
    private var outcomingDefaultBubbleColor: Int = 0
    private var outcomingDefaultBubblePressedColor: Int = 0
    private var outcomingDefaultBubbleSelectedColor: Int = 0

    private var outcomingImageOverlayDrawable: Int = 0
    private var outcomingDefaultImageOverlayPressedColor: Int = 0
    private var outcomingDefaultImageOverlaySelectedColor: Int = 0

    var outcomingDefaultBubblePaddingLeft: Int = 0
        private set
    var outcomingDefaultBubblePaddingRight: Int = 0
        private set
    var outcomingDefaultBubblePaddingTop: Int = 0
        private set
    var outcomingDefaultBubblePaddingBottom: Int = 0
        private set

    var outcomingTextColor: Int = 0
        private set
    var outcomingTextSize: Int = 0
        private set
    var outcomingTextStyle: Int = 0
        private set

    var outcomingTimeTextColor: Int = 0
        private set
    var outcomingTimeTextSize: Int = 0
        private set
    var outcomingTimeTextStyle: Int = 0
        private set

    var outcomingImageTimeTextColor: Int = 0
        private set
    var outcomingImageTimeTextSize: Int = 0
        private set
    var outcomingImageTimeTextStyle: Int = 0
        private set

    var dateHeaderPadding: Int = 0
        private set
    var dateHeaderFormat: String? = null
        private set
    var dateHeaderTextColor: Int = 0
        private set
    var dateHeaderTextSize: Int = 0
        private set
    var dateHeaderTextStyle: Int = 0
        private set

    private fun getMessageSelector(@ColorInt normalColor: Int, @ColorInt selectedColor: Int,
                                   @ColorInt pressedColor: Int, @DrawableRes shape: Int): Drawable {

        val drawable = DrawableCompat.wrap(getVectorDrawable(shape)!!).mutate()
        DrawableCompat.setTintList(
                drawable,
                ColorStateList(
                        arrayOf(intArrayOf(android.R.attr.state_selected), intArrayOf(android.R.attr.state_pressed), intArrayOf(-android.R.attr.state_pressed, -android.R.attr.state_selected)),
                        intArrayOf(selectedColor, pressedColor, normalColor)
                ))
        return drawable
    }

    fun getOutcomingBubbleDrawable(): Drawable? {
        return if (outcomingBubbleDrawable == -1) {
            getMessageSelector(outcomingDefaultBubbleColor, outcomingDefaultBubbleSelectedColor,
                    outcomingDefaultBubblePressedColor, R.drawable.shape_outcoming_message)
        } else {
            getDrawable(outcomingBubbleDrawable)
        }
    }

    fun getOutcomingImageOverlayDrawable(): Drawable? {
        return if (outcomingImageOverlayDrawable == -1) {
            getMessageSelector(Color.TRANSPARENT, outcomingDefaultImageOverlaySelectedColor,
                    outcomingDefaultImageOverlayPressedColor, R.drawable.shape_outcoming_message)
        } else {
            getDrawable(outcomingImageOverlayDrawable)
        }
    }

    fun getIncomingBubbleDrawable(): Drawable? {
        return if (incomingBubbleDrawable == -1) {
            getMessageSelector(incomingDefaultBubbleColor, incomingDefaultBubbleSelectedColor,
                    incomingDefaultBubblePressedColor, R.drawable.shape_incoming_message)
        } else {
            getDrawable(incomingBubbleDrawable)
        }
    }

    fun getIncomingImageOverlayDrawable(): Drawable? {
        return if (incomingImageOverlayDrawable == -1) {
            getMessageSelector(Color.TRANSPARENT, incomingDefaultImageOverlaySelectedColor,
                    incomingDefaultImageOverlayPressedColor, R.drawable.shape_incoming_message)
        } else {
            getDrawable(incomingImageOverlayDrawable)
        }
    }

    companion object {

        fun parse(context: Context, attrs: AttributeSet): MessagesListStyle {
            val style = MessagesListStyle(context, attrs)
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessagesList)

            style.textAutoLinkMask = typedArray.getInt(R.styleable.MessagesList_textAutoLink, 0)
            style.incomingTextLinkColor = typedArray.getColor(R.styleable.MessagesList_incomingTextLinkColor,
                    style.systemAccentColor)
            style.outcomingTextLinkColor = typedArray.getColor(R.styleable.MessagesList_outcomingTextLinkColor,
                    style.systemAccentColor)

            style.incomingAvatarWidth = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarWidth,
                    style.getDimension(R.dimen.message_avatar_width))
            style.incomingAvatarHeight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarHeight,
                    style.getDimension(R.dimen.message_avatar_height))

            style.incomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_incomingBubbleDrawable, -1)
            style.incomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleColor,
                    style.getColor(R.color.white_two))
            style.incomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubblePressedColor,
                    style.getColor(R.color.white_two))
            style.incomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleSelectedColor,
                    style.getColor(R.color.cornflower_blue_two_24))

            style.incomingImageOverlayDrawable = typedArray.getResourceId(R.styleable.MessagesList_incomingImageOverlayDrawable, -1)
            style.incomingDefaultImageOverlayPressedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultImageOverlayPressedColor,
                    style.getColor(R.color.transparent))
            style.incomingDefaultImageOverlaySelectedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultImageOverlaySelectedColor,
                    style.getColor(R.color.cornflower_blue_light_40))

            style.incomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingLeft,
                    style.getDimension(R.dimen.message_padding_left))
            style.incomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingRight,
                    style.getDimension(R.dimen.message_padding_right))
            style.incomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingTop,
                    style.getDimension(R.dimen.message_padding_top))
            style.incomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingBottom,
                    style.getDimension(R.dimen.message_padding_bottom))
            style.incomingTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTextColor,
                    style.getColor(R.color.dark_grey_two))
            style.incomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTextSize,
                    style.getDimension(R.dimen.message_text_size))
            style.incomingTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingTextStyle, Typeface.NORMAL)

            style.incomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTimeTextColor,
                    style.getColor(R.color.warm_grey_four))
            style.incomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTimeTextSize,
                    style.getDimension(R.dimen.message_time_text_size))
            style.incomingTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingTimeTextStyle, Typeface.NORMAL)

            style.incomingImageTimeTextColor = typedArray.getColor(R.styleable.MessagesList_incomingImageTimeTextColor,
                    style.getColor(R.color.warm_grey_four))
            style.incomingImageTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingImageTimeTextSize,
                    style.getDimension(R.dimen.message_time_text_size))
            style.incomingImageTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingImageTimeTextStyle, Typeface.NORMAL)

            style.outcomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_outcomingBubbleDrawable, -1)
            style.outcomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleColor,
                    style.getColor(R.color.cornflower_blue_two))
            style.outcomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubblePressedColor,
                    style.getColor(R.color.cornflower_blue_two))
            style.outcomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleSelectedColor,
                    style.getColor(R.color.cornflower_blue_two_24))

            style.outcomingImageOverlayDrawable = typedArray.getResourceId(R.styleable.MessagesList_outcomingImageOverlayDrawable, -1)
            style.outcomingDefaultImageOverlayPressedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultImageOverlayPressedColor,
                    style.getColor(R.color.transparent))
            style.outcomingDefaultImageOverlaySelectedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultImageOverlaySelectedColor,
                    style.getColor(R.color.cornflower_blue_light_40))

            style.outcomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingLeft,
                    style.getDimension(R.dimen.message_padding_left))
            style.outcomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingRight,
                    style.getDimension(R.dimen.message_padding_right))
            style.outcomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingTop,
                    style.getDimension(R.dimen.message_padding_top))
            style.outcomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingBottom,
                    style.getDimension(R.dimen.message_padding_bottom))
            style.outcomingTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTextColor,
                    style.getColor(R.color.white))
            style.outcomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTextSize,
                    style.getDimension(R.dimen.message_text_size))
            style.outcomingTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingTextStyle, Typeface.NORMAL)

            style.outcomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTimeTextColor,
                    style.getColor(R.color.white60))
            style.outcomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTimeTextSize,
                    style.getDimension(R.dimen.message_time_text_size))
            style.outcomingTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingTimeTextStyle, Typeface.NORMAL)

            style.outcomingImageTimeTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingImageTimeTextColor,
                    style.getColor(R.color.warm_grey_four))
            style.outcomingImageTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingImageTimeTextSize,
                    style.getDimension(R.dimen.message_time_text_size))
            style.outcomingImageTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingImageTimeTextStyle, Typeface.NORMAL)

            style.dateHeaderPadding = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderPadding,
                    style.getDimension(R.dimen.message_date_header_padding))
            style.dateHeaderFormat = typedArray.getString(R.styleable.MessagesList_dateHeaderFormat)
            style.dateHeaderTextColor = typedArray.getColor(R.styleable.MessagesList_dateHeaderTextColor,
                    style.getColor(R.color.warm_grey_two))
            style.dateHeaderTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderTextSize,
                    style.getDimension(R.dimen.message_date_header_text_size))
            style.dateHeaderTextStyle = typedArray.getInt(R.styleable.MessagesList_dateHeaderTextStyle, Typeface.NORMAL)

            typedArray.recycle()

            return style
        }
    }
}
