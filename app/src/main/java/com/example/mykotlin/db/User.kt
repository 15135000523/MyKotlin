package com.example.mykotlin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    @ColumnInfo(name = "first_name")val firstName:String?,
    @ColumnInfo(name = "last_name")val lastName:String?)