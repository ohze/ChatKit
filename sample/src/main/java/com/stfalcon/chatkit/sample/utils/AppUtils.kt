package com.stfalcon.chatkit.sample.utils

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.showToast(@StringRes text: Int, isLong: Boolean){
    this.showToast(this.getString(text), isLong)
}

fun Context.showToast(text: String, isLong: Boolean) {
    Toast.makeText(this, text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}
