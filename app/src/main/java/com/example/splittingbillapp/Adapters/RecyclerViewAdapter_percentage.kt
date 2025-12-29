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

class RecyclerViewAdapter_percentage(private var items : List<Message>,
                                     private val onPercentageChanged: () -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter_percentage.ViewHolder>() {

    private var totalAmount: Double = 0.0

    // Holds percentage input for each item, default to 0
    private var percentageList = MutableList(items.size) { 0 }

    init {
        calculateTotalAmount()
    }
    class ViewHolder(itemView : View ) : RecyclerView.ViewHolder(itemView){

        var textView : TextView = itemView.findViewById(R.id.split_percentage)
        var editTextView :EditText = itemView.findViewById(R.id.get_percentage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesign_split_by_percentage,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item = items.get(position)

        holder.editTextView.setText(
            if (percentageList[position] > 0) percentageList[position].toString() else ""
        )

//        holder.editTextView.setText(items.toString())

        // Set the initial calculated amount
        holder.textView.text = "Customer "+(position+1) + getFormattedAmount(percentageList[position])

        // Remove any existing watchers before adding a new one
        holder.editTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().toIntOrNull() ?: 0

                // Temporarily update percentage list to simulate change
                val tempList = percentageList.toMutableList()
                tempList[position] = input

                val totalPercentage = tempList.sum()

                if (totalPercentage > 100) {
                    // Revert and notify user
                    holder.editTextView.error = "Total cannot exceed 100%"
                    holder.textView.text = ""
                    return
                }

                // Commit valid input
                percentageList[position] = input
                holder.textView.text = "Customer "+(position+1) + getFormattedAmount(input)
                onPercentageChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    private fun getFormattedAmount(percentage: Int): String {
        val amount = (percentage / 100.0) * totalAmount
        return "\n£ %.2f".format(amount)
    }

    private fun calculateTotalAmount() {
        totalAmount = items.sumOf { it.order.total.toDouble() }
    }

    fun updateData(newList: List<Message>) {
        this.items = newList
        this.percentageList = MutableList(newList.size) { 0 }
        calculateTotalAmount()
        notifyDataSetChanged()
    }

    fun getTotalDistributedAmount(): Double {
        return percentageList.sumOf { (it / 100.0) * totalAmount }
    }

    fun getPercentageList(): List<Int> = percentageList

    fun getTotalAmount(): Double = totalAmount
}