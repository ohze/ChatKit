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
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet

import com.stfalcon.chatkit.R
import com.stfalcon.chatkit.commons.Style

/**
 * Style for MessageInputStyle customization by xml attributes
 */
internal class MessageInputStyle private constructor(context: Context, attrs: AttributeSet) : Style(context, attrs) {

    var showAttachmentButton: Boolean = false
        private set

    private var attachmentButtonBackgroundId: Int = 0
    private var attachmentButtonDefaultBgColor: Int = 0
    private var attachmentButtonDefaultBgPressedColor: Int = 0
    private var attachmentButtonDefaultBgDisabledColor: Int = 0

    private var attachmentButtonIconId: Int = 0
    private var attachmentButtonDefaultIconColor: Int = 0
    private var attachmentButtonDefaultIconPressedColor: Int = 0
    private var attachmentButtonDefaultIconDisabledColor: Int = 0

    var attachmentButtonWidth: Int = 0
        private set
    var attachmentButtonHeight: Int = 0
        private set
    var attachmentButtonMargin: Int = 0
        private set

    private var inputButtonBackgroundId: Int = 0
    private var inputButtonDefaultBgColor: Int = 0
    private var inputButtonDefaultBgPressedColor: Int = 0
    private var inputButtonDefaultBgDisabledColor: Int = 0

    private var inputButtonIconId: Int = 0
    private var inputButtonDefaultIconColor: Int = 0
    private var inputButtonDefaultIconPressedColor: Int = 0
    private var inputButtonDefaultIconDisabledColor: Int = 0

    var inputButtonWidth: Int = 0
        private set
    var inputButtonHeight: Int = 0
        private set
    var inputButtonMargin: Int = 0
        private set

    var inputMaxLines: Int = 0
        private set
    var inputHint: String? = null
        private set
    var inputText: String? = null
        private set

    var inputTextSize: Int = 0
        private set
    var inputTextColor: Int = 0
        private set
    var inputHintColor: Int = 0
        private set

    var inputBackground: Drawable? = null
        private set
    var inputCursorDrawable: Drawable? = null
        private set

    var inputDefaultPaddingLeft: Int = 0
        private set
    var inputDefaultPaddingRight: Int = 0
        private set
    var inputDefaultPaddingTop: Int = 0
        private set
    var inputDefaultPaddingBottom: Int = 0
        private set

    private fun getSelector(@ColorInt normalColor: Int, @ColorInt pressedColor: Int,
                            @ColorInt disabledColor: Int, @DrawableRes shape: Int): Drawable {

        val drawable = DrawableCompat.wrap(getVectorDrawable(shape)!!).mutate()
        DrawableCompat.setTintList(
                drawable,
                ColorStateList(
                        arrayOf(intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_pressed), intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed), intArrayOf(-android.R.attr.state_enabled)),
                        intArrayOf(normalColor, pressedColor, disabledColor)
                ))
        return drawable
    }

    val attachmentButtonBackground: Drawable?
        get() = if (attachmentButtonBackgroundId == -1) {
            getSelector(attachmentButtonDefaultBgColor, attachmentButtonDefaultBgPressedColor,
                    attachmentButtonDefaultBgDisabledColor, R.drawable.mask)
        } else {
            getDrawable(attachmentButtonBackgroundId)
        }

    val attachmentButtonIcon: Drawable?
        get() = if (attachmentButtonIconId == -1) {
            getSelector(attachmentButtonDefaultIconColor, attachmentButtonDefaultIconPressedColor,
                    attachmentButtonDefaultIconDisabledColor, R.drawable.ic_add_attachment)
        } else {
            getDrawable(attachmentButtonIconId)
        }

    val inputButtonBackground: Drawable?
        get() = if (inputButtonBackgroundId == -1) {
            getSelector(inputButtonDefaultBgColor, inputButtonDefaultBgPressedColor,
                    inputButtonDefaultBgDisabledColor, R.drawable.mask)
        } else {
            getDrawable(inputButtonBackgroundId)
        }

    val inputButtonIcon: Drawable?
        get() = if (inputButtonIconId == -1) {
            getSelector(inputButtonDefaultIconColor, inputButtonDefaultIconPressedColor,
                    inputButtonDefaultIconDisabledColor, R.drawable.ic_send)
        } else {
            getDrawable(inputButtonIconId)
        }

    companion object {

        private val DEFAULT_MAX_LINES = 5

        fun parse(context: Context, attrs: AttributeSet): MessageInputStyle {
            val style = MessageInputStyle(context, attrs)
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageInput)

            style.showAttachmentButton = typedArray.getBoolean(R.styleable.MessageInput_showAttachmentButton, false)

            style.attachmentButtonBackgroundId = typedArray.getResourceId(R.styleable.MessageInput_attachmentButtonBackground, -1)
            style.attachmentButtonDefaultBgColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultBgColor,
                    style.getColor(R.color.white_four))
            style.attachmentButtonDefaultBgPressedColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultBgPressedColor,
                    style.getColor(R.color.white_five))
            style.attachmentButtonDefaultBgDisabledColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultBgDisabledColor,
                    style.getColor(R.color.transparent))

            style.attachmentButtonIconId = typedArray.getResourceId(R.styleable.MessageInput_attachmentButtonIcon, -1)
            style.attachmentButtonDefaultIconColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultIconColor,
                    style.getColor(R.color.cornflower_blue_two))
            style.attachmentButtonDefaultIconPressedColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultIconPressedColor,
                    style.getColor(R.color.cornflower_blue_two_dark))
            style.attachmentButtonDefaultIconDisabledColor = typedArray.getColor(R.styleable.MessageInput_attachmentButtonDefaultIconDisabledColor,
                    style.getColor(R.color.cornflower_blue_light_40))

            style.attachmentButtonWidth = typedArray.getDimensionPixelSize(R.styleable.MessageInput_attachmentButtonWidth, style.getDimension(R.dimen.input_button_width))
            style.attachmentButtonHeight = typedArray.getDimensionPixelSize(R.styleable.MessageInput_attachmentButtonHeight, style.getDimension(R.dimen.input_button_height))
            style.attachmentButtonMargin = typedArray.getDimensionPixelSize(R.styleable.MessageInput_attachmentButtonMargin, style.getDimension(R.dimen.input_button_margin))

            style.inputButtonBackgroundId = typedArray.getResourceId(R.styleable.MessageInput_inputButtonBackground, -1)
            style.inputButtonDefaultBgColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultBgColor,
                    style.getColor(R.color.cornflower_blue_two))
            style.inputButtonDefaultBgPressedColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultBgPressedColor,
                    style.getColor(R.color.cornflower_blue_two_dark))
            style.inputButtonDefaultBgDisabledColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultBgDisabledColor,
                    style.getColor(R.color.white_four))

            style.inputButtonIconId = typedArray.getResourceId(R.styleable.MessageInput_inputButtonIcon, -1)
            style.inputButtonDefaultIconColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultIconColor,
                    style.getColor(R.color.white))
            style.inputButtonDefaultIconPressedColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultIconPressedColor,
                    style.getColor(R.color.white))
            style.inputButtonDefaultIconDisabledColor = typedArray.getColor(R.styleable.MessageInput_inputButtonDefaultIconDisabledColor,
                    style.getColor(R.color.warm_grey))

            style.inputButtonWidth = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonWidth, style.getDimension(R.dimen.input_button_width))
            style.inputButtonHeight = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonHeight, style.getDimension(R.dimen.input_button_height))
            style.inputButtonMargin = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonMargin, style.getDimension(R.dimen.input_button_margin))

            style.inputMaxLines = typedArray.getInt(R.styleable.MessageInput_inputMaxLines, DEFAULT_MAX_LINES)
            style.inputHint = typedArray.getString(R.styleable.MessageInput_inputHint)
            style.inputText = typedArray.getString(R.styleable.MessageInput_inputText)

            style.inputTextSize = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputTextSize, style.getDimension(R.dimen.input_text_size))
            style.inputTextColor = typedArray.getColor(R.styleable.MessageInput_inputTextColor, style.getColor(R.color.dark_grey_two))
            style.inputHintColor = typedArray.getColor(R.styleable.MessageInput_inputHintColor, style.getColor(R.color.warm_grey_three))

            style.inputBackground = typedArray.getDrawable(R.styleable.MessageInput_inputBackground)
            style.inputCursorDrawable = typedArray.getDrawable(R.styleable.MessageInput_inputCursorDrawable)

            typedArray.recycle()

            style.inputDefaultPaddingLeft = style.getDimension(R.dimen.input_padding_left)
            style.inputDefaultPaddingRight = style.getDimension(R.dimen.input_padding_right)
            style.inputDefaultPaddingTop = style.getDimension(R.dimen.input_padding_top)
            style.inputDefaultPaddingBottom = style.getDimension(R.dimen.input_padding_bottom)

            return style
        }
    }

}
