package com.example.androidroomexample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDao {

    @Insert
    suspend fun  insert(customer: Customer):Long

    @Update
    suspend fun  update(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Query("Delete from Customers")
    suspend fun deleteAll()

    @Query("select * from Customers")
    fun getAllCustomers():LiveData<List<Customer>>
}