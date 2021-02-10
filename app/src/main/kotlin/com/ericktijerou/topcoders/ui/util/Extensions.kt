package com.ericktijerou.topcoders.ui.util

import android.content.Context
import android.widget.Toast
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