package com.example.androidroomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidroomexample.databinding.ActivityMainBinding
import com.example.androidroomexample.db.CustomerDb
import com.example.androidroomexample.db.CustomerRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao=CustomerDb.getInstance(application).customerDao
        var repository = CustomerRepository(dao)
        val factory = CustomerViewModelFactory(repository)
        customerViewModel= ViewModelProvider(this,factory)[CustomerViewModel::class.java]
        binding.customerViewModel=customerViewModel
        binding.lifecycleOwner=this
        displayCustomers()
    }
    private fun displayCustomers(){
        customerViewModel.customers.observe(this, Observer {
            Log.i("salam",it.toString())
        })
    }
}