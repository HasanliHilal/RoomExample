package com.example.androidroomexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidroomexample.db.Customer
import com.example.androidroomexample.db.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel() {

    val customers = repository.customers
    private var isUpdareOrDelete = false
    private lateinit var customerToUpdateOrDelete: Customer

    val inputName = MutableLiveData<String>()
    val inputSurName = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearButtonText = MutableLiveData<String>()


    private val _statusMessage = MutableLiveData<Event<String>>()
    val showStatusMessage: LiveData<Event<String>>
        get() = _statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (isUpdareOrDelete) {
            customerToUpdateOrDelete.name = inputName.value!!
            customerToUpdateOrDelete.surName = inputSurName.value!!
            update(customerToUpdateOrDelete)
        } else {
            val name = inputName.value!!
            val surName = inputSurName.value!!
            insert(Customer(0, name, surName))
            inputName.value = ""
            inputSurName.value = ""
        }

    }

    fun clearAllOrDelete() {
        if (isUpdareOrDelete) {
            delete(customerToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            val newRowId = repository.insert(customer)
            withContext(Dispatchers.Main) {
                if (newRowId > -1) {
                    _statusMessage.value = Event("Inserted Customer! $newRowId")
                } else {
                    _statusMessage.value = Event("Error Occurred!")
                }
            }
        }
    }

    private fun update(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRows = repository.update(customer)
            withContext(Dispatchers.Main) {
                if (numberOfRows > 0) {
                    inputName.value = ""
                    inputSurName.value = "customer.surName"
                    isUpdareOrDelete = false
                    saveOrUpdateButtonText.value = "SAve"
                    clearButtonText.value = "ClearAll"
                    _statusMessage.value = Event("$numberOfRows Updated Customer")
                } else {
                    _statusMessage.value = Event("Error Occured")
                }
            }
        }
    }

    private fun delete(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRowsDeleteed = repository.delete(customer)
            withContext(Dispatchers.Main) {
                if(numberOfRowsDeleteed>0) {
                    inputName.value = ""
                    inputSurName.value = "customer.surName"
                    isUpdareOrDelete = false
                    saveOrUpdateButtonText.value = "SAve"
                    clearButtonText.value = "ClearAll"
                    _statusMessage.value = Event("$numberOfRowsDeleteed Deleted Customer")
                }else
                {
                    _statusMessage.value = Event("Error Occured")
                }
            }
        }
    }

    private fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
           val numberOfRowsDeleteed = repository.deleteAll()
            withContext(Dispatchers.Main) {
                if(numberOfRowsDeleteed>0){
                    _statusMessage.value = Event("$numberOfRowsDeleteed Cleared Customer")
                }else{
                    _statusMessage.value = Event("Error Occured")
                }
            }
        }
    }

    fun initUpdateAndDelete(customer: Customer) {
        inputName.value = customer.name
        inputSurName.value = customer.surName
        isUpdareOrDelete = true
        customerToUpdateOrDelete = customer
        saveOrUpdateButtonText.value = "Update"
        clearButtonText.value = "Delete"
    }
}