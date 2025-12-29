package com.example.splittingbillapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.Models.Item
import com.example.splittingbillapp.Models.Order
import com.example.splittingbillapp.R

class RecyclerViewAdapter_Inner_Item(private val items: List<Item>) :
    RecyclerView.Adapter<RecyclerViewAdapter_Inner_Item.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.itemName)
        val qty : TextView = view.findViewById(R.id.itemQty)
        val price : TextView= view.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.singlerowdesign_for_inner_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.item_name
        holder.qty.text = "${item.qty}×"
        holder.price.text = "£${item.finalTotal}"
    }
}