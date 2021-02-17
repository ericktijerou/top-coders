package com.ericktijerou.topcoders.ui.mapper

import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.domain.entity.User
import com.ericktijerou.topcoders.ui.entity.CoderView
import com.ericktijerou.topcoders.ui.util.toJoinedDate

fun User.toView(): CoderView {
    val hasCompany = company.isNotEmpty()
    return CoderView(
        name = name,
        username = username,
        avatarUrl = avatarUrl,
        bio = bio,
        hasCompany = hasCompany,
        info = if (hasCompany) company else createdAt.toJoinedDate(),
        infoIcon = if (hasCompany) R.drawable.ic_org else R.drawable.ic_clock,
        url = url,
        followersCount = followersCount.toString(),
        stargazerCount = stargazerCount.toString(),
        primaryLanguage = languagePrimary,
        colorLanguage = languageColor
    )
}