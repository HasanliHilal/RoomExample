package com.example.androidroomexample.db

class CustomerRepository(private val customerDao: CustomerDao) {

    val customers = customerDao.getAllCustomers()

    suspend fun insert(customer: Customer):Long {
       return customerDao.insert(customer)
    }

    suspend fun update(customer: Customer):Int {
        return customerDao.update(customer)
    }
    suspend fun delete(customer: Customer) : Int{
        return customerDao.delete(customer)
    }
    suspend fun deleteAll(): Int {
        return customerDao.deleteAll()
    }
}