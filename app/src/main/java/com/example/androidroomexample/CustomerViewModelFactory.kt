package com.example.androidroomexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.androidroomexample.db.CustomerRepository

class CustomerViewModelFactory(private val repository: CustomerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(CustomerViewModel::class.java)){
            return  CustomerViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown error")
    }
}