package com.example.splittingbillapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.Models.NeworderResponse
import com.example.splittingbillapp.anotherItem
import com.example.splittingbillapp.R

class RecyclerViewAdapter_split_by_evenly(private var items : List<Message>) :
    RecyclerView.Adapter<RecyclerViewAdapter_split_by_evenly.ViewHolder>() {

    companion object {
        var totalAmount: Double = 0.0
    }

    private var amountPerPerson: Double = 0.0
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var textView : TextView = itemView.findViewById(R.id.split_evenly)
        var customer_name : TextView = itemView.findViewById(R.id.customer_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesign_for_split_evenly,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.customer_name.text = "Customer " + (position+1)

        holder.textView.text = "£ %.2f".format(amountPerPerson)

    }

    fun updateData(newList: List<Message>) {
        this.items = newList
        calculateTotalAndAmount()
        notifyDataSetChanged()
    }
    private fun calculateTotalAndAmount() {
        totalAmount = items.sumOf { it.order.total.toDouble() }
        amountPerPerson = if (items.isNotEmpty()) totalAmount / items.size else 0.0
    }

}