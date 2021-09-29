package com.example.mykotlin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("select * from user where uid IN (:userIds)")
    fun loadDataById(vararg userIds: Int): List<User>

    @Insert
    fun insertData(vararg users: User)

    @Delete
    fun deleteData(user: User)

    @Query("delete from user where uid IN (:userIds)")
    fun deleteDataFromId(vararg userIds: Int)
}