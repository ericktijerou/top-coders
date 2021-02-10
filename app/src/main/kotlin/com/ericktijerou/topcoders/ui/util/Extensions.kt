package com.ericktijerou.topcoders.ui.util

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.showErrorMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.toJoinedDate(): String {
    return this.toDate()?.formatToViewDateDefaults().orEmpty()
}

fun String.toDate(): Date?  {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return dateFormat.parse(this)
}

fun Date.formatToViewDateDefaults(): String {
    val sdf= SimpleDateFormat.getDateInstance(DateFormat.LONG , Locale.getDefault())
    return sdf.format(this).capitalize(Locale.getDefault())
}

fun Context.isDarkThemeOn(): Boolean{
    return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

fun isOreo() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O

@ColorInt
fun Context.getAttributeColor(@AttrRes attrId: Int): Int {
    return obtainStyledAttributes(intArrayOf(attrId)).use {
        it.getColor(0, Color.BLACK)
    }
}