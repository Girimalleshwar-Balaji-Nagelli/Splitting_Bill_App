package com.example.splittingbillapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.ExpandableMessage
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.R
import com.example.splittingbillapp.User

class RecyclerViewAdapter_Item(private var users : List<ExpandableMessage>) : RecyclerView.Adapter<RecyclerViewAdapter_Item.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var userName : TextView = itemView.findViewById(R.id.userName)
        var textView : TextView = itemView.findViewById(R.id.userAmount)
        val userCard = itemView.findViewById<CardView>(R.id.userCard)
        val itemRecyclerView = itemView.findViewById<RecyclerView>(R.id.itemRecyclerView)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowdesign_for_sbi,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val expandableMessage = users[position]
        val message = expandableMessage.message

        holder.userName.text = "Customer " + (position + 1)
        holder.textView.text = message.order.total

//        holder.titleTextView.text = message.userName

        holder.itemRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.itemRecyclerView.adapter = RecyclerViewAdapter_Inner_Item(message.item) // or any list inside message


        holder.itemRecyclerView.visibility = if (expandableMessage.isExpanded) View.VISIBLE else View.GONE

        holder.userCard.setOnClickListener {
            expandableMessage.isExpanded = !expandableMessage.isExpanded
            notifyItemChanged(position)
        }

        holder.itemRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter_Inner_Item(expandableMessage.message.item)
            visibility = if (expandableMessage.isExpanded) View.VISIBLE else View.GONE
        }

        holder.userCard.setOnClickListener {
            expandableMessage.isExpanded = !expandableMessage.isExpanded
            notifyItemChanged(position)
        }

    }

    fun updateData(newData: List<Message>) {
        users = newData.map { ExpandableMessage(it) }
        notifyDataSetChanged()
    }

}