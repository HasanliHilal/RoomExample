package com.example.androidroomexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidroomexample.databinding.ListItemBinding
import com.example.androidroomexample.db.Customer

class MyRecylerviewAdapter (private val customers: List<Customer>,
                            private val clickListener:(Customer)->Unit):
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:ListItemBinding=DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(customers[position],clickListener)
    }

    override fun getItemCount(): Int {
        return  customers.size
    }
}

class MyViewHolder(val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(customer: Customer,clickListener:(Customer)->Unit){
        binding.txtName.text=customer.name
        binding.txtSurname.text = customer.surName
        binding.lisItemLayout.setOnClickListener {
            clickListener(customer)
        }
    }
}