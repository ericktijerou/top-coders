package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coder")
data class CoderEntity(
    @PrimaryKey
    var id: Int? = 0
)