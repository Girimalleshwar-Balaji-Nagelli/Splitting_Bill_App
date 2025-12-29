package com.example.splittingbillapp.Adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.anotherItem
import com.example.splittingbillapp.R

class RecyclerViewAdapter_Amount(private var items : List<Message>,) : RecyclerView.Adapter<RecyclerViewAdapter_Amount.ViewHolder>() {

    private var totalAmount: Double = 0.0
    private val enteredAmounts = MutableList<Double?>(items.size) { null }

    init {
        calculateTotalAmount()
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val amountEditText: EditText = itemView.findViewById(R.id.editTextAmount)
        val customer_name : TextView = itemView.findViewById(R.id.customer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesgin_for_sba,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.customer_name.text = "Customer " + (position + 1)

        holder.amountEditText.setText(
            enteredAmounts[position]?.let { "%.2f".format(it) } ?: ""
        )

        holder.amountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                holder.amountEditText.removeTextChangedListener(this)

                val newValue = s.toString().toDoubleOrNull()
                val previousValue = enteredAmounts[position] ?: 0.0
                val currentTotal = enteredAmounts.filterNotNull().sum() - previousValue

                if (newValue != null && (currentTotal + newValue > totalAmount)) {
                    holder.amountEditText.setText("")
                    holder.amountEditText.error = "Exceeds total amount!"
                    enteredAmounts[position] = null
                } else {
                    enteredAmounts[position] = newValue
                }

                holder.amountEditText.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    private fun calculateTotalAmount() {
        totalAmount = items.sumOf { it.order.total.toDouble() }
    }

    fun updateData(newList: List<Message>) {
        this.items = newList
        enteredAmounts.clear()
        enteredAmounts.addAll(List(newList.size) { null })
        calculateTotalAmount()
        notifyDataSetChanged()
    }

    fun getUserEnteredAmounts(): List<Double?> = enteredAmounts

    fun getTotalAmount(): Double = totalAmount
}