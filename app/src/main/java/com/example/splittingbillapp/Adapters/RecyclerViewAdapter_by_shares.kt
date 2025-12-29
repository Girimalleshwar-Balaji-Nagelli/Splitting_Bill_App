package com.example.splittingbillapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.CounterItem
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.anotherItem
import com.example.splittingbillapp.R

class RecyclerViewAdapter_by_shares(private var items : List<Message>,
                                    private val onSharesChanged: (() -> Unit)? = null ) :
    RecyclerView.Adapter<RecyclerViewAdapter_by_shares.ViewHolder>() {

    private var sharesList = MutableList(items.size) { 1 }
    private var totalAmount: Double = 0.0

    init {
        calculateTotalAmount()
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val decrementButton: TextView = itemView.findViewById(R.id.decrementButton)
        val incrementButton: TextView = itemView.findViewById(R.id.incrementButton)
        val countText: TextView = itemView.findViewById(R.id.countText)
        val amountText: TextView = itemView.findViewById(R.id.textview_shares)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesign_for_sbs,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.countText.text = sharesList[position].toString()
        holder.amountText.text = "Customer "+ (position+1) +"\n"+ getFormattedAmount(position)

        holder.incrementButton.setOnClickListener {
            sharesList[position]++
            notifyDataSetChanged() // 👈 refresh all rows to recalculate amounts
        }

        holder.decrementButton.setOnClickListener {
            if (sharesList[position] > 0) {
                sharesList[position]--
                notifyDataSetChanged()
            }
        }

    }


    private fun getFormattedAmount(position: Int): String {
//        val totalShares = sharesList.sum().takeIf { it != 0 } ?: return "£ 0.00"
        val totalShares = sharesList.sum()
        if (totalShares == 0) return "£ 0.00" // Avoid divide by zero

        val itemShare = sharesList[position]
        val amount = (itemShare.toDouble() / totalShares) * totalAmount
        return "£ %.2f".format(amount)
    }

    private fun calculateTotalAmount() {
        totalAmount = items.sumOf { it.order.total.toDouble() }
    }

    fun updateData(newList: List<Message>) {
        items = newList
        sharesList = MutableList(newList.size) { 1 }
        calculateTotalAmount()
        notifyDataSetChanged()
    }

    fun getSharesList(): List<Int> = sharesList

    fun getTotalAmount(): Double = totalAmount
}