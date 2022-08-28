package com.petocare.infra.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.petocare.infra.database.model.UserDetailsModel
import io.reactivex.Single

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM UserDetail")
    fun getUserDetails(): Single<List<UserDetailsModel>>
}