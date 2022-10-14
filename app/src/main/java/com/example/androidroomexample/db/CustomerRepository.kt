package com.example.androidroomexample.db

class CustomerRepository(private val customerDao: CustomerDao) {

    val customers = customerDao.getAllCustomers()

    suspend fun insert(customer: Customer) {
        customerDao.insert(customer)
    }

    suspend fun update(customer: Customer) {
        customerDao.update(customer)
    }
    suspend fun delete(customer: Customer){
        customerDao.delete(customer)
    }
    suspend fun deleteAll(){
        customerDao.deleteAll()
    }
}