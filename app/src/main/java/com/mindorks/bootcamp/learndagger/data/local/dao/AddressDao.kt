package com.mindorks.bootcamp.learndagger.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mindorks.bootcamp.learndagger.data.local.entity.UserAddress
import io.reactivex.Single


@Dao
interface AddressDao {

    @Delete
    fun delete(address: UserAddress): Single<Int>

    @Query("SELECT * FROM addresses")
    fun getAllAddresses(): Single<List<UserAddress>>   // returns the list of users

    @Insert
    fun insertMany(vararg userAddress: UserAddress): Single<List<Long>>  // returns the list of users id which have been inserted
}