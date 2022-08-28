package com.petocare.infra.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserDetail")
data class UserDetailsModel(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var user_id: String,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "address")
    var address: String? = null,

    @ColumnInfo(name = "mobile")
    var mobile: String? = null

)
