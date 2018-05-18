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

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date?.format(template: DateFormatter.Template): String = this.format(template.get())

fun Date?.format(format: String): String =
        if (this == null) ""
        else SimpleDateFormat(format, Locale.getDefault())
            .format(this)

class DateFormatter private constructor() {
    /**
     * Interface used to format dates before they were displayed (e.g. dialogs time, messages date headers etc.).
     */
    interface Formatter {
        /**
         * Formats an string representation of the date object.
         *
         * @param date The date that should be formatted.
         * @return Formatted text.
         */
        fun format(date: Date): String
    }

    enum class Template(private val template: String) {
        STRING_DAY_MONTH_YEAR("d MMMM yyyy"),
        STRING_DAY_MONTH("d MMMM"),
        TIME("HH:mm");

        fun get(): String = template
    }
}

fun Date.isSameDay(d: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = this
    val cal2 = Calendar.getInstance()
    cal2.time = d
    return cal1.isSameDay(cal2)
}

fun Calendar.isSameDay(c: Calendar): Boolean {
    return this.get(Calendar.ERA) == c.get(Calendar.ERA) &&
            this.get(Calendar.YEAR) == c.get(Calendar.YEAR) &&
            this.get(Calendar.DAY_OF_YEAR) == c.get(Calendar.DAY_OF_YEAR)
}

fun Calendar.isYesterday(): Boolean {
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)
    return this.isSameDay(yesterday)
}

fun Calendar.isToday(): Boolean = this.isSameDay(Calendar.getInstance())
fun Date.isToday(): Boolean = this.isSameDay(Calendar.getInstance().time)

fun Date.isYesterday(): Boolean {
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)
    return this.isSameDay(yesterday.time)
}

fun Date.isSameYear(d: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = this
    val cal2 = Calendar.getInstance()
    cal2.time = d
    return cal1.isSameYear(cal2)
}
fun Calendar.isSameYear(c: Calendar): Boolean =
    this.get(Calendar.ERA) == c.get(Calendar.ERA) &&
        this.get(Calendar.YEAR) == c.get(Calendar.YEAR)

fun Calendar.isCurrentYear(): Boolean = this.isSameYear(Calendar.getInstance())
fun Date.isCurrentYear(): Boolean = this.isSameYear(Calendar.getInstance().time)