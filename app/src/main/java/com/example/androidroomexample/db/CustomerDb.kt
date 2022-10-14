package com.example.androidroomexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Customer::class], version = 1)
abstract class CustomerDb:RoomDatabase() {

    abstract val customerDao:CustomerDao
    companion object{
        @Volatile
        private var INSTANCE:CustomerDb? = null
        fun getInstance(context:Context):CustomerDb{
            synchronized(this){
                var instance= INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomerDb::class.java,
                        "CustomerDB"
                    ).build()
                    INSTANCE=instance
                }
                return  instance
            }
        }
    }
}