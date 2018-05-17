package com.stfalcon.chatkit.sample.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

/*
 * Created by troy379 on 06.04.17.
 */
fun Int.durationString(): String {
    val date = Date((this * 1000).toLong())
    val formatter = SimpleDateFormat(if (this >= 3600) "HH:mm:ss" else "mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    return formatter.format(date)
}