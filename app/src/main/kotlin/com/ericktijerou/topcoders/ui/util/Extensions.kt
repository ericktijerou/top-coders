package com.ericktijerou.topcoders.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.showErrorMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.toJoinedDate(): String {
    return this.toDate()?.formatToViewDateDefaults().orEmpty()
}

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return dateFormat.parse(this)
}

@SuppressLint("DefaultLocale")
fun Date.formatToViewDateDefaults(): String {
    val sdf = SimpleDateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
    return sdf.format(this).capitalize()
}

fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

fun isOreo() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O

@ColorInt
fun Context.getAttributeColor(@AttrRes attrId: Int): Int {
    return obtainStyledAttributes(intArrayOf(attrId)).use {
        it.getColor(0, Color.BLACK)
    }
}

fun TabLayout.setupWithViewPager2(viewPager: ViewPager2) {
    val pageTitleProvider = viewPager.adapter as PageTitleProvider
    val strategy = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        tab.text = pageTitleProvider.getPageTitle(position)
    }
    val mediator = TabLayoutMediator(this, viewPager, strategy)
    mediator.attach()
}

fun Int.dpToPixels(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
)

fun Int.dpToPixelsInt(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()

fun View.visible(value: Boolean = true) {
    visibility = if (value) View.VISIBLE else View.GONE
}

fun View.gone() {
    visibility = View.GONE
}