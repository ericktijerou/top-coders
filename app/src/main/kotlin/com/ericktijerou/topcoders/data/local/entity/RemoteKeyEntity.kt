package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKey")
data class RemoteKeyEntity(@PrimaryKey val username: String, val prevKey: Int?, val nextKey: Int?)
