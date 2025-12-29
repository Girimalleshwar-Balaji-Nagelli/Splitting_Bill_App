package com.example.splittingbillapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.Models.Item
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.R
import com.example.splittingbillapp.anotherItem

class RecyclerViewAdapter_bill_summary(private var items : List<Message>) : RecyclerView.Adapter<RecyclerViewAdapter_bill_summary.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        var textView_name : TextView = itemView.findViewById(R.id.itemName)
//        var textView_qty : TextView = itemView.findViewById(R.id.itemQty)
//        var textView_price : TextView = itemView.findViewById(R.id.itemPrice)

        var recyclerView : RecyclerView = itemView.findViewById(R.id.recview_bill_summary_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesign_for_bill_summary,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item1 : Message = items[position]

        holder.recyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.recyclerView.adapter = RecyclerViewAdapter_Inner_Item(item1.item) // or any list inside message


//        holder.textView_price.text = item1.get(0).finalTotal.toString()
//        holder.textView_qty.text = item1.get(0).qty.toString()
//        holder.textView_name.text = item1.get(0).item_name


   }

    fun updateData(newList: List<Message>) {
        this.items = newList
        notifyDataSetChanged()
    }
}