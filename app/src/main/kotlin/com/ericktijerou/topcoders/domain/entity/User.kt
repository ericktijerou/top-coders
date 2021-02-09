package com.ericktijerou.topcoders.domain.entity

import com.ericktijerou.topcoders.ui.entity.UserView

data class User(val name: String, val username: String)

fun User.toView() = UserView(
    name = name,
    username = username
)