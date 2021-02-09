package com.ericktijerou.topcoders.ui.mapper

import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.domain.entity.User
import com.ericktijerou.topcoders.ui.entity.UserView

fun User.toView(): UserView {
    val hasCompany = company.isNotEmpty()
    return UserView(
        name = name,
        username = username,
        avatarUrl = avatarUrl,
        bio = bio,
        info = if (hasCompany) company else createdAt,
        infoIcon = if (hasCompany) R.drawable.ic_business else R.drawable.ic_clock
    )
}