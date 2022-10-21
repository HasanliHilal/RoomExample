package com.example.androidroomexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="Id")
    val id:Int,
    @ColumnInfo(name="Name")
    var name:String,
    @ColumnInfo(name="SurName")
    var surName:String
)
