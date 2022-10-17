package com.example.androidroomexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidroomexample.db.Customer
import com.example.androidroomexample.db.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel() {

    val customers = repository.customers

    val inputName = MutableLiveData<String>()
    val inputSurName = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val surName = inputSurName.value!!
        insert(Customer(0,name,surName))
        inputName.value=""
        inputSurName.value =""
    }

    fun clearAllOrDelete() {
        clearAll()
    }

    fun insert(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(customer)
        }
    }

    fun update(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(customer)
        }
    }

    fun delete(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(customer)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}