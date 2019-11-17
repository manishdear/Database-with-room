package com.mindorks.bootcamp.learndagger.data.local.dao

import androidx.room.*
import com.mindorks.bootcamp.learndagger.data.local.entity.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Single<Long>  // returns the id of inserted row

    @Update
    fun update(user: User): Single<Int>   // returns the number of row have updated

    @Delete
    fun  delete(user: User): Single<Int>  // returns the number of row deleted

    @Insert
    fun insertMany(vararg users: User): Single<List<Long>>  // returns the list of users id which have been inserted

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<User>>   // returns the list of users

    @Query("SELECT * FROM users WHERE id= :id LIMIT 1")
    fun getUserById(id: Long): Single<User>   // returns the wanted user

    @Query("SELECT count(*) FROM users")
    fun count(): Single<Int>  // retruns the count of rows
}